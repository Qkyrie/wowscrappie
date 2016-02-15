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
import com.deswaef.wowscrappie.item.domain.Item;
import com.deswaef.wowscrappie.item.repository.ItemRepository;
import com.deswaef.wowscrappie.realm.domain.Realm;
import com.deswaef.wowscrappie.realm.repository.RealmRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BattlenetAuctionsImporterImpl implements BattlenetAuctionsImporter {

    private Log logger = LogFactory.getLog(this.getClass());

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

    @Transactional
    public void importAuctions(long l) {
        importAuctions(realmRepository.findOne(l).orElseThrow(() -> new HeurekaException("That realm is not available")));
    }

    private void importAuctions(Realm realm) {
        try {
            AuctionResponse latestAuctionInformation = auctionFetcher.getLatestAuctionInformation(realm);
            List<AuctionResponse.AuctionResponseFile> files = latestAuctionInformation.getFiles();
            if (!files.isEmpty()) {
                if (needsUpdate(realm, files)) {
                    AuctionResponse.AuctionResponseFile responseFile = files.get(0);
                    AuctionSnapshot latestSnapshot = auctionFetcher.getLatestSnapshot(responseFile.getUrl());
                    List<AuctionHouseSnapshot> converted = convert(realm, latestSnapshot.getAuctions(), responseFile);
                    save(converted);
                    updateSnapshotConfiguration(realm, responseFile);
                }
            }
        } catch (HeurekaException e) {
            throw new HeurekaException(String.format("unable to import auctions for realm %s", realm.getName()), e);
        }
    }

    private void updateSnapshotConfiguration(Realm realm, AuctionResponse.AuctionResponseFile auctionSnapshot) {
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

    private void save(List<AuctionHouseSnapshot> converted) {
        converted
                .stream()
                .forEach(convert -> {
                    try {
                        auctionHouseSnapshotRepository.save(
                                convert.setItem(getItemOrFetchFromWowhead(convert.getItem().getId()))
                        );
                    } catch (Exception ex) {
                        logger.error("unable to save an auction", ex);
                    }
                });
    }

    private List<AuctionHouseSnapshot> convert(Realm realm, List<AuctionItem> auctionItems, AuctionResponse.AuctionResponseFile responseFile) {
        Map<Long, List<AuctionItem>> mapPerItem = auctionItems
                .stream()
                .collect(Collectors.groupingBy(AuctionItem::getItem));
        List<AuctionHouseSnapshot> generatedSnapshots = new ArrayList<>();
        for (Map.Entry<Long, List<AuctionItem>> auctionsPerItem : mapPerItem.entrySet()) {
            Optional<AuctionHouseSnapshot> generatedAuctionHouseSnapshot = snapshotFromEntry(realm, responseFile, auctionsPerItem);
            if (generatedAuctionHouseSnapshot.isPresent()) {
                generatedSnapshots.add(
                        generatedAuctionHouseSnapshot.get()
                );
            }
        }
        return generatedSnapshots;
    }

    private Optional<AuctionHouseSnapshot> snapshotFromEntry(Realm realm, AuctionResponse.AuctionResponseFile responseFile, Map.Entry<Long, List<AuctionItem>> auctionsPerItem) {
        try {


            DoubleSummaryStatistics bidStatistics = auctionsPerItem.getValue()
                    .stream()
                    .mapToDouble(a -> a.getBid() / a.getQuantity())
                    .summaryStatistics();

            DoubleSummaryStatistics buyoutStatistics = auctionsPerItem.getValue()
                    .stream()
                    .mapToDouble(a -> a.getBuyout() / a.getQuantity())
                    .summaryStatistics();

            return Optional.ofNullable(new AuctionHouseSnapshot()
                    .setRealm(realm)
                    .setMinimumBid(bidStatistics.getMin())
                    .setMaximumBid(bidStatistics.getMax())
                    .setAverageBid(bidStatistics.getAverage())
                    .setMinimumBuyout(buyoutStatistics.getMin())
                    .setMaximumBuyout(buyoutStatistics.getMax())
                    .setAverageBuyout(buyoutStatistics.getAverage())
                    .setQuantity(auctionsPerItem.getValue()
                            .stream()
                            .mapToLong(AuctionItem::getQuantity)
                            .count()
                    )
                    .setExportTime(new Date(responseFile.getLastModified()))
                    .setItem(getOrUpdateItem(auctionsPerItem.getKey()))
            );
        } catch (HeurekaException e) {
            logger.info(String.format("Item could not be fetched from wowhead, should try again later: %s", e.getMessage()));
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
