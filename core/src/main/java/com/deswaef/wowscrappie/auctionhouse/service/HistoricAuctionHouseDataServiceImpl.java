package com.deswaef.wowscrappie.auctionhouse.service;

import com.deswaef.wowscrappie.auctionhouse.domain.HistoricAuctionHouseSnapshot;
import com.deswaef.wowscrappie.auctionhouse.repository.HistoricAuctionHouseSnapshotRepository;
import com.deswaef.wowscrappie.infrastructure.exception.WowscrappieException;
import com.deswaef.wowscrappie.item.domain.Item;
import com.deswaef.wowscrappie.item.repository.ItemRepository;
import com.deswaef.wowscrappie.realm.domain.Realm;
import com.deswaef.wowscrappie.realm.repository.RealmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rx.Observable;

import java.util.Optional;

@Service
public class HistoricAuctionHouseDataServiceImpl implements HistoricAuctionHouseDataService {


    @Autowired
    private HistoricAuctionHouseSnapshotRepository historicAuctionHouseSnapshotRepository;
    @Autowired
    private RealmRepository realmRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Override
    @Transactional(readOnly = true)
    public Observable<HistoricAuctionHouseSnapshot> findByItemIdAndRealm(Long itemId, Long realmId) {
        Optional<Item> optionalItem = itemRepository.findOne(itemId);
        Optional<Realm> optionalRealm = realmRepository.findOne(realmId);
        if (optionalItem.isPresent()) {
            if (optionalRealm.isPresent()) {
                return Observable.from(
                        historicAuctionHouseSnapshotRepository.findByItemIdAndRealmId(itemId, realmId)
                );
            } else {
                return Observable.error(new WowscrappieException("That realm is not yet indexed or does not exist"));
            }
        } else {
            return Observable.error(new WowscrappieException("That is not yet indexed or does not exist"));
        }
    }
}
