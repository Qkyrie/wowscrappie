package com.deswaef.wowscrappie.auctionhouse.service;

import com.deswaef.wowscrappie.auctionhouse.domain.AuctionHouseSnapshotRegionStatistics;
import com.deswaef.wowscrappie.realm.domain.Locality;

public interface AuctionHouseSnapshotRegionStatisticsService {

    AuctionHouseSnapshotRegionStatistics getStatisticsForRegion(long itemId, Locality locality);

}
