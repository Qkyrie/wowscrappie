package com.deswaef.wowscrappie.auctionhouse.service;

import com.deswaef.wowscrappie.auctionhouse.domain.AuctionHouseCategory;
import com.deswaef.wowscrappie.auctionhouse.domain.AuctionHouseSubCategory;
import com.deswaef.wowscrappie.auctionhouse.repository.AuctionHouseSubCategoryRepository;
import com.deswaef.wowscrappie.auctionhouse.repository.AuctionhouseCategoryRepository;
import com.deswaef.wowscrappie.item.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rx.Observable;

import java.util.Optional;

@Service
public class AuctionHouseCategoryServiceImpl implements AuctionHouseCategoryService {

    @Autowired
    private AuctionhouseCategoryRepository auctionhouseCategoryRepository;
    @Autowired
    private AuctionHouseSubCategoryRepository auctionHouseSubCategoryRepository;

    @Override
    @Transactional(readOnly = true)
    public Observable<AuctionHouseCategory> findAll() {
        return Observable.from(
                auctionhouseCategoryRepository.findAll()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Observable<Item> findItems(long auctionhouseSubCategory) {
        Optional<AuctionHouseSubCategory> subCategory = auctionHouseSubCategoryRepository.findOne(auctionhouseSubCategory);
        if (subCategory.isPresent()) {
            return Observable.from(
                    subCategory.get().items()
            );
        } else {
            return Observable.empty();
        }
    }

}
