package com.deswaef.heureka.battlenet.wow.continuous;

import com.deswaef.wowscrappie.auctionhouse.domain.AuctionHouseSnapshotConfiguration;
import com.deswaef.wowscrappie.auctionhouse.service.AuctionHouseSnapshotConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Profile("!integrationtest")
public class ScheduledAuctionHouseConfigurationResetter {

    @Autowired
    private AuctionHouseSnapshotConfigurationService auctionHouseSnapshotConfigurationService;

    @Scheduled(cron = "0 0,12 * * *")
    public void resetSchedule() {
        auctionHouseSnapshotConfigurationService
                .findAll()
                .map(AuctionHouseSnapshotConfiguration::getId)
                .forEach(auctionHouseSnapshotConfigurationService::requestUpdate);
    }

}
