package com.deswaef.wowscrappie.importingservice.battlenet.wow.continuous;

import com.deswaef.wowscrappie.auctionhouse.domain.AuctionHouseSnapshotConfiguration;
import com.deswaef.wowscrappie.auctionhouse.service.AuctionHouseSnapshotConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import rx.Observable;


@Component
@Profile("!integrationtest")
@ConditionalOnProperty(value = "com.deswaef.wowscrappie.jobs.importer.enabled", havingValue = "true")
public class AuctionsImportScheduler {

    @Autowired
    private AuctionHouseSnapshotConfigurationService auctionHouseSnapshotConfigurationService;
    @Autowired
    private BattlenetAuctionsImporter importer;

    @Scheduled(fixedDelay = 60_000)
    public void importJob() {
        findConfigurationsThatNeedUpdates()
                .forEach(element -> importer.importAuctions(element.getRealm().getId()));
    }


    private Observable<AuctionHouseSnapshotConfiguration> findConfigurationsThatNeedUpdates() {
        return auctionHouseSnapshotConfigurationService.findUpdatable();
    }


}
