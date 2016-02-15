package com.deswaef.wowscrappie.auctionhouse.service;

import com.deswaef.wowscrappie.auctionhouse.domain.AuctionHouseSnapshotConfiguration;
import com.deswaef.wowscrappie.auctionhouse.repository.AuctionHouseSnapshotConfigurationRepository;
import com.deswaef.wowscrappie.realm.domain.Realm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rx.Observable;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AuctionHouseSnapshotConfigurationServiceImpl implements AuctionHouseSnapshotConfigurationService {

    @Autowired
    private AuctionHouseSnapshotConfigurationRepository auctionHouseSnapshotRepository;

    @Override
    public Observable<AuctionHouseSnapshotConfiguration> findAll() {
        return Observable.from(auctionHouseSnapshotRepository.findAll());
    }

    @Override
    public Observable<AuctionHouseSnapshotConfiguration> findUpdatable() {
        return Observable.from(auctionHouseSnapshotRepository.findByNeedsUpdate(true));
    }

    @Override
    @Transactional
    public void requestUpdate(long configurationId) {
        auctionHouseSnapshotRepository.findOne(configurationId)
                .filter(element -> !element.isNeedsUpdate())
                .ifPresent(element -> {
                    auctionHouseSnapshotRepository.save(element.setNeedsUpdate(true));
                });
    }

    @Override
    @Transactional
    public void create(long realmId) {
        Optional<AuctionHouseSnapshotConfiguration> byRealmId = auctionHouseSnapshotRepository.findByRealmId(realmId);
        if (!byRealmId.isPresent()) {
            auctionHouseSnapshotRepository.save(
                    new AuctionHouseSnapshotConfiguration()
                            .setNeedsUpdate(true)
                            .setRealm(
                                    new Realm()
                                            .setId(realmId)
                            )
            );
        }
    }
}
