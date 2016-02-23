package com.deswaef.wowscrappie.auctionhouse.service;

import com.deswaef.wowscrappie.auctionhouse.domain.AuctionItem;
import com.deswaef.wowscrappie.auctionhouse.repository.AuctionItemRepository;
import com.deswaef.wowscrappie.item.domain.Item;
import com.deswaef.wowscrappie.realm.domain.Realm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rx.Observable;


@Service
public class AuctionItemServiceImpl implements AuctionItemService {

    @Autowired
    private AuctionItemRepository auctionItemRepository;

    @Transactional(readOnly = true)
    @Override
    public Observable<AuctionItem> findAllByItemAndRealm(Item item, Realm realm) {
        return Observable.from(auctionItemRepository.findAllByItemAndRealmId(item.getId(), realm.getId()));
    }

}
