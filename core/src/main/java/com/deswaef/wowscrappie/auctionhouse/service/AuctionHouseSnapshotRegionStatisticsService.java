package com.deswaef.wowscrappie.auctionhouse.service;

import com.deswaef.wowscrappie.auctionhouse.domain.AuctionHouseSnapshotRegionStatistic;
import com.deswaef.wowscrappie.realm.domain.Locality;

public interface AuctionHouseSnapshotRegionStatisticsService {

    AuctionHouseSnapshotRegionStatistic getStatisticsForRegion(long itemId, Locality locality);

}
