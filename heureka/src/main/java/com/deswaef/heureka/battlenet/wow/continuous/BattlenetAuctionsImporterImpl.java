package com.deswaef.heureka.battlenet.wow.continuous;

import com.deswaef.heureka.battlenet.wow.AuctionFetcher;
import com.deswaef.heureka.battlenet.wow.auctions.client.domain.AuctionItem;
import com.deswaef.heureka.battlenet.wow.auctions.client.domain.AuctionResponse;
import com.deswaef.heureka.battlenet.wow.domain.AuctionSnapshot;
import com.deswaef.heureka.infrastructure.exception.HeurekaException;
import com.deswaef.heureka.wowhead.client.ItemFetchingService;
import com.deswaef.wowscrappie.auctionhouse.domain.AuctionHouseSnapshot;
import com.deswaef.wowscrappie.auctionhouse.domain.AuctionHouseSnapshotConfiguration;
import com.deswaef.wowscrappie.auctionhouse.repository.AuctionHouseSnapshotConfigurationRepository;
import com.deswaef.wowscrappie.auctionhouse.repository.AuctionHouseSnapshotRepository;
import com.deswaef.wowscrappie.auctionhouse.repository.AuctionItemRepository;
import com.deswaef.wowscrappie.item.domain.Item;
import com.deswaef.wowscrappie.item.repository.ItemRepository;
import com.deswaef.wowscrappie.realm.domain.Realm;
import com.deswaef.wowscrappie.realm.repository.RealmRepository;
import com.deswaef.wowscrappie.realm.service.RealmService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.deswaef.heureka.battlenet.wow.auctions.client.domain.AuctionItem.convert;
import static java.lang.Double.NaN;

@Service
public class BattlenetAuctionsImporterImpl implements BattlenetAuctionsImporter {

    public static final double EMPTY = 0.00;
    private Log LOG = LogFactory.getLog(this.getClass());

    @Autowired
    private AuctionFetcher auctionFetcher;
    @Autowired
    private AuctionHouseSnapshotConfigurationRepository auctionHouseSnapshotConfigurationRepository;
    @Autowired
    private AuctionHouseSnapshotRepository auctionHouseSnapshotRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemFetchingService itemFetchingService;
    @Autowired
    private RealmRepository realmRepository;
    @Autowired
    private RealmService realmService;
    @Autowired
    private AuctionItemRepository auctionItemRepository;

    @Transactional
    public void importAuctions(long l) {
        importAuctions(realmRepository.findOne(l).orElseThrow(() -> new HeurekaException("That realm is not available")));
    }

    private void importAuctions(Realm realm) {
        try {
            List<AuctionResponse.AuctionResponseFile> files = auctionFetcher.getLatestAuctionInformation(realm).getFiles();
            if (!files.isEmpty() && needsUpdate(realm, files)) {
                files
                        .stream()
                        .forEach(responseFile -> {
                            AuctionSnapshot latestSnapshot = auctionFetcher.getLatestSnapshot(responseFile.getUrl());


                            List<Realm> realms = latestSnapshot.realms()
                                    .stream()
                                    .map(x -> realmService.findBySlugAndLocality(x.slug(), realm.getLocality()))
                                    .filter(Optional::isPresent)
                                    .map(Optional::get)
                                    .collect(Collectors.toList());

                            saveStatisticsToDatabase(
                                    convertToStatistics(realms, latestSnapshot.auctions(), responseFile)
                            );

                            saveAuctionsToElasticSearch(
                                    realms,
                                    realm.getLocality().getLocalityName(),
                                    latestSnapshot.auctions(),
                                    responseFile.getLastModified()
                            );

                            updateSnapshotConfiguration(realms, responseFile);
                        });
            } else {
                LOG.debug(String.format("Either there were no files, or we didn't need to update %s-%s yet", realm.getLocality().getLocalityName(), realm.getName()));
            }
        } catch (Exception e) {
            throw new HeurekaException(String.format("unable to import auctions for realm %s-%s", realm.getLocality().getLocalityName(), realm.getName()), e);
        }
    }

    private void saveAuctionsToElasticSearch(List<Realm> realms, String localityName, List<AuctionItem> auctions, Long lastModified) {
        Date exportTime = new Date(lastModified);
        realms
                .forEach(realm -> auctionItemRepository
                        .save(
                                auctions
                                        .parallelStream()
                                        .map(auction -> convert.apply(auction))
                                        .map(auction -> auction.setExportTime(exportTime)
                                                .setLocality(localityName)
                                                .setRealmName(realm.getName())
                                                .setRealmSlug(realm.getSlug())
                                                .setRealmId(realm.getId())
                                        )
                                        .collect(Collectors.toList())
                        ));
    }

    private void updateSnapshotConfiguration(List<Realm> realms, AuctionResponse.AuctionResponseFile auctionSnapshot) {
        realms
                .stream()
                .forEach(
                        realm -> {
                            Optional<AuctionHouseSnapshotConfiguration> byRealmId = auctionHouseSnapshotConfigurationRepository.findByRealmId(realm.getId());
                            if (byRealmId.isPresent()) {
                                AuctionHouseSnapshotConfiguration generatedSnapshotRealmConfiguration = byRealmId.get();
                                generatedSnapshotRealmConfiguration
                                        .setLastUpdate(new Date(auctionSnapshot.getLastModified()))
                                        .setNeedsUpdate(false);
                                auctionHouseSnapshotConfigurationRepository.save(generatedSnapshotRealmConfiguration);
                            } else {
                                AuctionHouseSnapshotConfiguration generatedSnapshotRealmConfiguration = new AuctionHouseSnapshotConfiguration()
                                        .setLastUpdate(new Date(auctionSnapshot.getLastModified()))
                                        .setNeedsUpdate(false)
                                        .setRealm(realm);
                                auctionHouseSnapshotConfigurationRepository.save(generatedSnapshotRealmConfiguration);
                            }
                        }
                );
    }

    private void saveStatisticsToDatabase(List<AuctionHouseSnapshot> converted) {
        converted
                .stream()
                .forEach(convert -> {
                    try {
                        auctionHouseSnapshotRepository.save(
                                convert.setItem(getItemOrFetchFromWowhead(convert.getItem().getId()))
                        );
                    } catch (Exception ex) {
                        LOG.error("unable to save an auction", ex);
                    }
                });
    }


    private List<AuctionHouseSnapshot> convertToStatistics(List<Realm> realms, List<AuctionItem> auctionItems, AuctionResponse.AuctionResponseFile responseFile) {
        Map<Long, List<AuctionItem>> mapPerItem = auctionItems
                .stream()
                .collect(Collectors.groupingBy(AuctionItem::item));
        List<AuctionHouseSnapshot> generatedSnapshots = new ArrayList<>();
        for (Map.Entry<Long, List<AuctionItem>> auctionsPerItem : mapPerItem.entrySet()) {
            snapshotFromEntry(responseFile, auctionsPerItem)
                    .ifPresent(x ->
                            generatedSnapshots.addAll(
                                    from(x, realms)
                            )
                    );
        }
        return generatedSnapshots;
    }

    private List<AuctionHouseSnapshot> from(AuctionHouseSnapshot auctionHouseSnapshot, List<Realm> realms) {
        return realms
                .parallelStream()
                .map(realm -> new AuctionHouseSnapshot()
                        .setAverageBid(auctionHouseSnapshot.getAverageBid())
                        .setAverageBuyout(auctionHouseSnapshot.getAverageBuyout())
                        .setMinimumBid(auctionHouseSnapshot.getMinimumBid())
                        .setMinimumBuyout(auctionHouseSnapshot.getMaximumBuyout())
                        .setMaximumBid(auctionHouseSnapshot.getMaximumBid())
                        .setMaximumBuyout(auctionHouseSnapshot.getMaximumBuyout())
                        .setItem(auctionHouseSnapshot.getItem())
                        .setQuantity(auctionHouseSnapshot.getQuantity())
                        .setStdevBid(auctionHouseSnapshot.getStdevBid())
                        .setStdevBuyout(auctionHouseSnapshot.getStdevBuyout())
                        .setMedianBid(auctionHouseSnapshot.getMedianBid())
                        .setMedianBuyout(auctionHouseSnapshot.getMedianBuyout())
                        .setRealm(realm)
                        .setExportTime(auctionHouseSnapshot.getExportTime()))
                .collect(Collectors.toList());
    }

    private Optional<AuctionHouseSnapshot> snapshotFromEntry(AuctionResponse.AuctionResponseFile responseFile, Map.Entry<Long, List<AuctionItem>> auctionsPerItem) {
        try {
            //calculate total count for items
            long totalQuantity = auctionsPerItem
                    .getValue()
                    .parallelStream()
                    .mapToLong(AuctionItem::quantity)
                    .sum();

            DescriptiveStatistics bidStatistics = new DescriptiveStatistics();

            auctionsPerItem
                    .getValue()
                    .parallelStream()
                    .map(value -> {
                        List<Double> bidList = new ArrayList<>();
                        for (int i = 0; i < value.quantity(); i++) {
                            if (value.bid() != null && value.bid() > 0) {
                                bidList.add((double) (value.bid() / value.quantity()));
                            }
                        }
                        return bidList;
                    })
                    .flatMap(Collection::stream)
                    .forEach(bidStatistics::addValue);

            DescriptiveStatistics buyoutStatistics = new DescriptiveStatistics();

            auctionsPerItem
                    .getValue()
                    .parallelStream()
                    .map(value -> {
                        List<Double> buyoutList = new ArrayList<>();
                        for (int i = 0; i < value.quantity(); i++) {
                            if (value.buyout() != null && value.buyout() > 0) {
                                buyoutList.add((double) (value.buyout() / value.quantity()));
                            }
                        }
                        return buyoutList;
                    })
                    .flatMap(Collection::stream)
                    .forEach(buyoutStatistics::addValue);

            return Optional.ofNullable(new AuctionHouseSnapshot()
                    .setMinimumBid(bidStatistics.getMin())
                    .setMaximumBid(bidStatistics.getMax())
                    .setAverageBid(bidStatistics.getMean())
                    .setMedianBid(bidStatistics.getPercentile(50))
                    .setStdevBid(bidStatistics.getStandardDeviation())
                    .setStdevBuyout(Double.compare(buyoutStatistics.getStandardDeviation(), NaN) == 0 ? EMPTY : buyoutStatistics.getStandardDeviation())
                    .setMinimumBuyout(Double.compare(buyoutStatistics.getMin(), NaN) == 0 ? EMPTY : buyoutStatistics.getMin())
                    .setMaximumBuyout(Double.compare(buyoutStatistics.getMax(), NaN) == 0 ? EMPTY : buyoutStatistics.getMax())
                    .setAverageBuyout(Double.compare(buyoutStatistics.getMean(), NaN) == 0 ? EMPTY : buyoutStatistics.getMean())
                    .setMedianBuyout(Double.compare(buyoutStatistics.getPercentile(50), NaN) == 0 ? EMPTY : buyoutStatistics.getPercentile(50))
                    .setQuantity(totalQuantity)
                    .setExportTime(new Date(responseFile.getLastModified()))
                    .setItem(getOrUpdateItem(auctionsPerItem.getKey()))
            );
        } catch (HeurekaException e) {
            LOG.info(String.format("Item could not be fetched from wowhead, should try again later: %s", e.getMessage()));
            return Optional.empty();
        }
    }

    private Item getOrUpdateItem(Long itemId) {
        return new Item().setId(itemId);
    }

    private Item getItemOrFetchFromWowhead(Long itemId) {
        return itemRepository.findOne(itemId).orElseGet(() ->
                itemRepository.saveAndFlush(
                        new Item()
                                .setId(itemId)
                                .setName(itemFetchingService.getItem(itemId).get().getItem().getName())
                )
        );
    }

    private boolean needsUpdate(Realm realm, List<AuctionResponse.AuctionResponseFile> files) {
        Optional<AuctionHouseSnapshotConfiguration> snapshotHistory = auctionHouseSnapshotConfigurationRepository.findByRealmId(realm.getId());
        if (!snapshotHistory.isPresent()) {
            return true;
        } else if (snapshotHistory.get().isNeedsUpdate()) {
            return snapshotHistory.get().getLastUpdate() == null || files.get(0).getLastModified() != snapshotHistory.get().getLastUpdate().getTime();
        } else {
            return false;
        }
    }

}
