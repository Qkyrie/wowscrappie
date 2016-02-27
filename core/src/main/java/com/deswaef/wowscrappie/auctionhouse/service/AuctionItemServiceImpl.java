package com.deswaef.wowscrappie.auctionhouse.service;

import com.deswaef.wowscrappie.auctionhouse.domain.AuctionItem;
import com.deswaef.wowscrappie.auctionhouse.repository.AuctionItemRepository;
import com.deswaef.wowscrappie.item.domain.Item;
import com.deswaef.wowscrappie.realm.domain.Realm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rx.Observable;

import java.util.Date;


@Service
public class AuctionItemServiceImpl implements AuctionItemService {

    @Autowired
    private AuctionItemRepository auctionItemRepository;

    @Transactional(readOnly = true)
    @Override
    public Observable<AuctionItem> findAllByItemAndRealm(Item item, Realm realm) {
        return Observable.from(auctionItemRepository.findAllByItemAndRealmId(item.getId(), realm.getId()));
    }

    @Override
    @Transactional(readOnly = true)
    public Observable<AuctionItem> findAllBetweenDates(long realmId, Date startDate, Date endDate) {
        return Observable.from(auctionItemRepository.findAllByRealmIdAndExportTimeBetween(realmId, startDate.getTime(), endDate.getTime())::iterator);
    }

}
