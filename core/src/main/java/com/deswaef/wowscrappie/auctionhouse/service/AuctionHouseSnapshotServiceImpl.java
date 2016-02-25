package com.deswaef.wowscrappie.auctionhouse.service;

import com.deswaef.wowscrappie.auctionhouse.domain.AuctionHouseSnapshot;
import com.deswaef.wowscrappie.auctionhouse.repository.AuctionHouseSnapshotRepository;
import com.deswaef.wowscrappie.infrastructure.exception.WowscrappieException;
import com.deswaef.wowscrappie.item.domain.Item;
import com.deswaef.wowscrappie.item.repository.ItemRepository;
import com.deswaef.wowscrappie.realm.domain.Locality;
import com.deswaef.wowscrappie.realm.domain.Realm;
import com.deswaef.wowscrappie.realm.repository.RealmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rx.Observable;

import java.util.Optional;

@Service
public class AuctionHouseSnapshotServiceImpl implements AuctionHouseSnapshotService {

    @Autowired
    private AuctionHouseSnapshotRepository auctionHouseSnapshotRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private RealmRepository realmRepository;

    @Override
    @Transactional(readOnly = true)
    public Observable<AuctionHouseSnapshot> findByItemIdAndRealm(Long itemId, Long realmId) {
        Optional<Item> optionalItem = itemRepository.findOne(itemId);
        Optional<Realm> optionalRealm = realmRepository.findOne(realmId);
        if (optionalItem.isPresent()) {
            if (optionalRealm.isPresent()) {
                return Observable.just(
                        auctionHouseSnapshotRepository
                                .findFirstByItemAndRealm(optionalItem.get(), optionalRealm.get()).orElseThrow(() -> new WowscrappieException("no latest snapshot found for that item/realm combination"))
                );
            } else {
                return Observable.error(new WowscrappieException("That realm is not yet indexed or does not exist"));
            }
        } else {
            return Observable.error(new WowscrappieException("That item is not yet indexed or does not exist"));
        }

    }

    @Override
    @Transactional(readOnly = true)
    public Observable<AuctionHouseSnapshot> findByItemIdAndLocality(long itemId, Locality locality) {
        Optional<Item> optionalItem = itemRepository.findOne(itemId);
        if (optionalItem.isPresent()) {
            return Observable.from(
                    auctionHouseSnapshotRepository.findByItemAndRealmLocality(optionalItem.get(), locality)
            );
        } else {
            return Observable.error(new WowscrappieException("That item is not yet indexed or does not exist"));
        }
    }
}
