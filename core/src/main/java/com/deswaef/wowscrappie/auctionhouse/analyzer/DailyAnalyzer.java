package com.deswaef.wowscrappie.auctionhouse.analyzer;

import com.deswaef.wowscrappie.auctionhouse.domain.AuctionItem;
import com.deswaef.wowscrappie.auctionhouse.domain.DailyAuctionSnapshot;
import com.deswaef.wowscrappie.auctionhouse.repository.AuctionItemRepository;
import com.deswaef.wowscrappie.auctionhouse.repository.DailyAuctionSnapshotRepository;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Double.NaN;

@Component
public class DailyAnalyzer {

    public static final double EMPTY = 0.00;

    @Autowired
    private AuctionItemRepository auctionItemService;
    @Autowired
    private DailyAuctionSnapshotRepository dailyAuctionSnapshotRepository;

    public void analyzeForDay(LocalDate day) {
        long epocMilliDay = day.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli();
        long epocMilliDayAfter = day.plusDays(1).atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli();

        Stream<AuctionItem> allByExportTimeBetween = auctionItemService.findAllByExportTimeBetween(epocMilliDay, epocMilliDayAfter);

        Map<Long, Map<Long, List<AuctionItem>>> collect = allByExportTimeBetween
                .collect(Collectors.groupingBy(
                        AuctionItem::getRealmId,
                        Collectors.groupingBy(AuctionItem::getItem)));

        dailyAuctionSnapshotRepository.save(
                collect
                        .entrySet()
                        .parallelStream()
                        .map(entry -> calculateStatisticsForRealm(day, entry.getKey(), entry.getValue()))
                        .flatMap(x -> x)
                        .collect(Collectors.toList())
        );
    }

    private Stream<DailyAuctionSnapshot> calculateStatisticsForRealm(LocalDate date, long realm, Map<Long, List<AuctionItem>> itemsPerItemId) {
        return itemsPerItemId
                .entrySet()
                .parallelStream()
                .map(entry -> calculateStatisticsForItem(date, realm, entry.getKey(), entry.getValue()))
                .filter(Optional::isPresent)
                .map(Optional::get);
    }

    public Optional<DailyAuctionSnapshot> calculateStatisticsForItem(LocalDate date, long realm, long item, List<AuctionItem> auctions) {
        List<AuctionItem> distinctAuctions = auctions
                .stream()
                .filter(distinctByKey(AuctionItem::getAuctionId))
                .collect(Collectors.toList());

        long totalQuantity = distinctAuctions
                .parallelStream()
                .mapToLong(AuctionItem::getQuantity)
                .sum();

        DescriptiveStatistics bidStatistics = new DescriptiveStatistics();

        distinctAuctions
                .parallelStream()
                .map(value -> {
                    List<Double> bidList = new ArrayList<>();
                    for (int i = 0; i < value.getQuantity(); i++) {
                        if (value.getBid() > 0) {
                            bidList.add((double) (value.getBid() / value.getQuantity()));
                        }
                    }
                    return bidList;
                })
                .flatMap(Collection::stream)
                .forEach(bidStatistics::addValue);

        DescriptiveStatistics buyoutStatistics = new DescriptiveStatistics();

        distinctAuctions
                .parallelStream()
                .map(value -> {
                    List<Double> buyoutList = new ArrayList<>();
                    for (int i = 0; i < value.getQuantity(); i++) {
                        if (value.getBuyout() > 0) {
                            buyoutList.add((double) (value.getBuyout() / value.getQuantity()));
                        }
                    }
                    return buyoutList;
                })
                .flatMap(Collection::stream)
                .forEach(buyoutStatistics::addValue);

        return
                Optional.of(
                        new DailyAuctionSnapshot()
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
                                .setExportTime(Date.from(date.atStartOfDay().toInstant(ZoneOffset.UTC)))
                                .setItemId(item)
                                .setRealmId(realm)
                );

    }

    public static <T> Predicate<T> distinctByKey(Function<? super T,Object> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

}
