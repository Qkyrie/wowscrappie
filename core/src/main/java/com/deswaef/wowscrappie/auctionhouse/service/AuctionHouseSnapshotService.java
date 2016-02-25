package com.deswaef.wowscrappie.auctionhouse.service;

import com.deswaef.wowscrappie.auctionhouse.domain.AuctionHouseSnapshot;
import com.deswaef.wowscrappie.realm.domain.Locality;
import rx.Observable;

public interface AuctionHouseSnapshotService {

    Observable<AuctionHouseSnapshot> findByItemIdAndRealm(Long itemId, Long realmId);
    Observable<AuctionHouseSnapshot> findByItemIdAndLocality(long itemId, Locality locality);

}
