package com.deswaef.wowscrappie.auctionhouse.service;

import com.deswaef.wowscrappie.auctionhouse.domain.DailyAuctionSnapshot;

import java.util.stream.Stream;

public interface DailyAuctionHouseSnapshotService {
    Stream<DailyAuctionSnapshot> lastDays(long realmId, long itemId, int amountOfDays);
}
