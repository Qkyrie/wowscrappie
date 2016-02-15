package com.deswaef.wowscrappie.auctionhouse.service;

import com.deswaef.wowscrappie.auctionhouse.domain.HistoricAuctionHouseSnapshot;
import com.deswaef.wowscrappie.auctionhouse.repository.HistoricAuctionHouseSnapshotRepository;
import com.deswaef.wowscrappie.realm.repository.RealmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rx.Observable;

@Service
public class HistoricAuctionHouseDataServiceImpl implements HistoricAuctionHouseDataService {


    @Autowired
    private HistoricAuctionHouseSnapshotRepository historicAuctionHouseSnapshotRepository;
    @Autowired
    private RealmRepository realmRepository;

    @Override
    @Transactional(readOnly = true)
    public Observable<HistoricAuctionHouseSnapshot> findByItemIdAndRealm(Long itemId, Long realmId) {
        return Observable.from(
                historicAuctionHouseSnapshotRepository.findByItemIdAndRealmId(itemId, realmId)
        );
    }
}
