package com.deswaef.wowscrappie.auctionhouse.service;

import com.deswaef.wowscrappie.auctionhouse.domain.DailyAuctionSnapshot;
import com.deswaef.wowscrappie.auctionhouse.repository.DailyAuctionSnapshotRepository;
import org.elasticsearch.common.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Service
public class DailyAuctionHouseSnapshotServiceImpl implements DailyAuctionHouseSnapshotService {

    @Autowired
    private DailyAuctionSnapshotRepository dailyAuctionSnapshotRepository;

    @Override
    @Transactional(readOnly = true)
    public Stream<DailyAuctionSnapshot> lastDays(long realmId, long itemId, int amountOfDays) {
        LocalDate currentDate = LocalDate.now();
        LocalDate someDaysAgo = currentDate.minusDays(amountOfDays);
        return dailyAuctionSnapshotRepository.findAllByRealmIdAndItemIdAndExportTimeBetween(
                realmId, itemId, someDaysAgo.toDate().getTime(), currentDate.toDate().getTime()
        ).filter(distinctByKey(DailyAuctionSnapshot::getExportTime));
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

}
