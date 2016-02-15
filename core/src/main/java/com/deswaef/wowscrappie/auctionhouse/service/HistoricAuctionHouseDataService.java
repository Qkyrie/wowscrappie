package com.deswaef.wowscrappie.auctionhouse.service;

import com.deswaef.wowscrappie.auctionhouse.domain.HistoricAuctionHouseSnapshot;
import rx.Observable;

public interface HistoricAuctionHouseDataService {
    Observable<HistoricAuctionHouseSnapshot> findByItemIdAndRealm(Long itemId, Long realmId);
}
