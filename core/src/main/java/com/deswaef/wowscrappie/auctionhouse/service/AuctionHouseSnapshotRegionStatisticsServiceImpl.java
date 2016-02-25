package com.deswaef.wowscrappie.auctionhouse.service;

import com.deswaef.wowscrappie.auctionhouse.domain.AuctionHouseSnapshot;
import com.deswaef.wowscrappie.auctionhouse.domain.AuctionHouseSnapshotRegionStatistic;
import com.deswaef.wowscrappie.infrastructure.exception.WowscrappieException;
import com.deswaef.wowscrappie.item.domain.Item;
import com.deswaef.wowscrappie.item.service.ItemService;
import com.deswaef.wowscrappie.realm.domain.Locality;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rx.Observable;

import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;

@Service
public class AuctionHouseSnapshotRegionStatisticsServiceImpl implements AuctionHouseSnapshotRegionStatisticsService {

    @Autowired
    private AuctionHouseSnapshotService auctionHouseSnapshotService;
    @Autowired
    private ItemService itemService;

    @Override
    @Transactional(readOnly = true)
    public AuctionHouseSnapshotRegionStatistic getStatisticsForRegion(long itemId, Locality locality) {

        Optional<Item> item = itemService.findOne(itemId);
        if (!item.isPresent()) {
            throw new WowscrappieException("That item is not yet indexed or does not exist");
        }


        Observable<AuctionHouseSnapshot> snapshotsAsObservable = auctionHouseSnapshotService
                .findByItemIdAndLocality(itemId, locality);
        List<AuctionHouseSnapshot> snapshots = snapshotsAsObservable.toList()
                .toBlocking()
                .single();

        DescriptiveStatistics quantityStatistics = new DescriptiveStatistics();
        DescriptiveStatistics averageBuyoutStatistics = new DescriptiveStatistics();
        DescriptiveStatistics averageBidStatistics = new DescriptiveStatistics();
        DescriptiveStatistics medianBuyoutStatistics = new DescriptiveStatistics();
        DescriptiveStatistics medianBidStatistics = new DescriptiveStatistics();

        snapshots
                .parallelStream()
                .forEach(element -> {
                    quantityStatistics.addValue(element.getQuantity());
                    LongStream.rangeClosed(1, element.getQuantity())
                            .forEach(time -> {
                                averageBuyoutStatistics.addValue(element.getAverageBuyout());
                                averageBidStatistics.addValue(element.getAverageBid());
                                medianBuyoutStatistics.addValue(element.getMedianBuyout());
                                medianBidStatistics.addValue(element.getMedianBid());
                            });
                });

        return new AuctionHouseSnapshotRegionStatistic()
                .medianQuantityPerServer((long) quantityStatistics.getPercentile(50))
                .averageQuantityPerServer((long) quantityStatistics.getMean())
                .totalQuantity((long) quantityStatistics.getSum())
                .medianBid(medianBidStatistics.getPercentile(50))
                .medianBuyout(medianBuyoutStatistics.getPercentile(50))
                .averageBid(averageBidStatistics.getMean())
                .averageBuyout(averageBuyoutStatistics.getMean())
                .locality(locality)
                .item(item.get());

    }
}
