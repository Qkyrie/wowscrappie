package com.deswaef.wowscrappie.auctionhouse.service;

import com.deswaef.wowscrappie.auctionhouse.domain.AuctionHouseCategory;
import com.deswaef.wowscrappie.item.domain.Item;
import rx.Observable;

public interface AuctionHouseCategoryService {
    Observable<AuctionHouseCategory> findAll();

    Observable<Item> findItems(long auctionhouseSubCategory);
}
