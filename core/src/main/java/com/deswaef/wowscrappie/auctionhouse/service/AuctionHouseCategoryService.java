package com.deswaef.wowscrappie.auctionhouse.service;

import com.deswaef.wowscrappie.auctionhouse.domain.AuctionHouseCategory;
import com.deswaef.wowscrappie.auctionhouse.domain.AuctionHouseSubCategory;
import com.deswaef.wowscrappie.item.domain.Item;
import rx.Observable;

import java.util.Optional;

public interface AuctionHouseCategoryService {
    Observable<AuctionHouseCategory> findAll();

    Observable<Item> findItems(long auctionhouseSubCategory);

    Optional<AuctionHouseSubCategory> subCategoryById(Long subCategoryId);
}
