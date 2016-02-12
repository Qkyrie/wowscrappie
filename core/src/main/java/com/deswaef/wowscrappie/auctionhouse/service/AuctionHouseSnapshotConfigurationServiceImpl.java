package com.deswaef.wowscrappie.auctionhouse.service;

import com.deswaef.wowscrappie.auctionhouse.domain.AuctionHouseSnapshotConfiguration;
import com.deswaef.wowscrappie.auctionhouse.repository.AuctionHouseSnapshotConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rx.Observable;

@Service
@Transactional(readOnly = true)
public class AuctionHouseSnapshotConfigurationServiceImpl implements AuctionHouseSnapshotConfigurationService {

    @Autowired
    private AuctionHouseSnapshotConfigurationRepository auctionHouseSnapshotRepository;

    @Override
    public Observable<AuctionHouseSnapshotConfiguration> findAll() {
        return Observable.from(auctionHouseSnapshotRepository.findAll());
    }
}
