package com.deswaef.wowscrappie.auctionhouse.analyzer;

import com.deswaef.wowscrappie.applicationevent.ApplicationEventTypeEnum;
import com.deswaef.wowscrappie.applicationevent.service.ApplicationEventService;
import com.deswaef.wowscrappie.auctionhouse.domain.DailyAuctionSnapshot;
import com.deswaef.wowscrappie.auctionhouse.domain.ReadableAuctionItem;
import com.deswaef.wowscrappie.auctionhouse.repository.AuctionItemNativeRepository;
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

import static java.lang.Double.NaN;

@Component
public class DailyAnalyzer {

    public static final double EMPTY = 0.00;

    @Autowired
    private AuctionItemRepository auctionItemService;
    @Autowired
    private DailyAuctionSnapshotRepository dailyAuctionSnapshotRepository;
    @Autowired
    private ApplicationEventService applicationEventService;
    @Autowired
    private AuctionItemNativeRepository auctionItemNativeRepository;

    public void analyzeForDay(LocalDate day, long realm) {
        applicationEventService.create(
                ApplicationEventTypeEnum.JOB_STEP_STARTED,
                "Started daily analyzer for day " + day + " and realm " + realm
        );

        try {
            long epocMilliDay = day.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli();
            long epocMilliDayAfter = day.plusDays(1).atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli();

            //List<AuctionItem> allAuctions = auctionItemService.findAllByRealmIdAndExportTimeBetween(realm, epocMilliDay, epocMilliDayAfter);
            List<ReadableAuctionItem> allAuctions = auctionItemNativeRepository.findByRealmAndDate(realm, epocMilliDay, epocMilliDayAfter);
            Map<Long, List<ReadableAuctionItem>> collect =
                    allAuctions
                            .stream()
                            .collect(Collectors.groupingBy(
                                    ReadableAuctionItem::getItem,
                                    Collectors.toList()));

            List<DailyAuctionSnapshot> collectedValues = collect
                    .entrySet()
                    .parallelStream()
                    .map(x -> calculateStatisticsForItem(day, realm, x.getKey(), x.getValue()))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());

            if (!collectedValues.isEmpty()) {
                dailyAuctionSnapshotRepository.save(collectedValues);
            }
        } catch (Exception ex) {
            applicationEventService.create(
                    ApplicationEventTypeEnum.JOB_STEP_FAILED,
                    "Failed daily analyzer for day " + day + " and realm " + realm
            );
        } finally {
            applicationEventService.create(
                    ApplicationEventTypeEnum.JOB_STEP_ENDED,
                    "Done daily analyzer for day " + day + " and realm " + realm
            );
        }
    }

    public Optional<DailyAuctionSnapshot> calculateStatisticsForItem(LocalDate date, long realm, long item, List<ReadableAuctionItem> auctions) {
        List<ReadableAuctionItem> distinctAuctions = auctions
                .stream()
                .filter(distinctByKey(ReadableAuctionItem::getAuctionId))
                .collect(Collectors.toList());

        long totalQuantity = distinctAuctions
                .parallelStream()
                .mapToLong(ReadableAuctionItem::getQuantity)
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

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

}
