package com.deswaef.wowscrappie.auctionhouse.service;

import com.deswaef.wowscrappie.auctionhouse.domain.AuctionHouseSnapshot;
import com.deswaef.wowscrappie.realm.domain.Locality;
import com.deswaef.wowscrappie.realm.domain.Realm;
import rx.Observable;

import java.util.List;
import java.util.function.BiFunction;

public interface AuctionHouseSnapshotService {

    Observable<AuctionHouseSnapshot> findByItemIdAndRealm(Long itemId, Long realmId);

    Observable<AuctionHouseSnapshot> findByItemIdAndLocality(long itemId, Locality locality);

    <T> List<T> findTopItemsByRealm(Realm realm, BiFunction<String, Long, T> mapper);
}
