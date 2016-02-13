package com.deswaef.wowscrappie.auctionhouse.service;

import com.deswaef.wowscrappie.auctionhouse.domain.AuctionHouseSnapshotConfiguration;
import rx.Observable;

public interface AuctionHouseSnapshotConfigurationService {

    Observable<AuctionHouseSnapshotConfiguration> findAll();

    Observable<AuctionHouseSnapshotConfiguration> findUpdatable();

    void requestUpdate(long configurationId);

    void create(long realmId);

}
