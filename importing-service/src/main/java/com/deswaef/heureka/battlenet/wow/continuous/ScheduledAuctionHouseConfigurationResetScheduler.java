package com.deswaef.heureka.battlenet.wow.continuous;

import com.deswaef.wowscrappie.applicationevent.ApplicationEventTypeEnum;
import com.deswaef.wowscrappie.applicationevent.service.ApplicationEventService;
import com.deswaef.wowscrappie.auctionhouse.domain.AuctionHouseSnapshotConfiguration;
import com.deswaef.wowscrappie.auctionhouse.service.AuctionHouseSnapshotConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Profile("!integrationtest")
public class ScheduledAuctionHouseConfigurationResetScheduler {

    @Autowired
    private AuctionHouseSnapshotConfigurationService auctionHouseSnapshotConfigurationService;
    @Autowired
    private ApplicationEventService applicationEventService;

    @Scheduled(cron = "0 0 */1 * * *")
    public void resetSchedule() {
        applicationEventService.create(
                ApplicationEventTypeEnum.JOB_STARTED,
                "scheduler for resetting our ah-configurations started"
        );
        auctionHouseSnapshotConfigurationService
                .findAll()
                .map(AuctionHouseSnapshotConfiguration::getId)
                .forEach(auctionHouseSnapshotConfigurationService::requestUpdate);
        applicationEventService.create(
                ApplicationEventTypeEnum.JOB_ENDED,
                "scheduler for resetting our ah-configurations ended"
        );
    }

}
