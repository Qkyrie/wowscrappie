package com.deswaef.wowscrappie.auctionhouse.service;

import com.deswaef.wowscrappie.auctionhouse.domain.AuctionHouseSnapshot;
import rx.Observable;

public interface AuctionHouseSnapshotService {

    Observable<AuctionHouseSnapshot> findByItemIdAndRealm(Long itemId, Long realmId);


}
