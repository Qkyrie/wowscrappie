package com.deswaef.wowscrappie.auctionhouse.service;

import com.deswaef.wowscrappie.auctionhouse.domain.AuctionItem;
import com.deswaef.wowscrappie.item.domain.Item;
import com.deswaef.wowscrappie.realm.domain.Realm;
import rx.Observable;

public interface AuctionItemService {
    Observable<AuctionItem> findAllByItemAndRealm(Item item, Realm realm);
}
