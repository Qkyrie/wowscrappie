package com.deswaef.wowscrappie.auctionhouse.continuous;

import com.deswaef.wowscrappie.applicationevent.ApplicationEventTypeEnum;
import com.deswaef.wowscrappie.applicationevent.service.ApplicationEventService;
import com.deswaef.wowscrappie.auctionhouse.exporter.AuctionsExporter;
import com.deswaef.wowscrappie.auctionhouse.repository.AuctionHouseSnapshotConfigurationRepository;
import com.deswaef.wowscrappie.auctionhouse.repository.AuctionItemNativeRepository;
import com.deswaef.wowscrappie.auctionhouse.repository.DailyAuctionSnapshotNativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@ConditionalOnProperty(value = "com.deswaef.wowscrappie.jobs.oldsnapshotdeleter.enabled", havingValue = "true")
public class OldDataDeleterScheduler {

    @Autowired
    private AuctionsExporter auctionsExporter;
    @Autowired
    private AuctionHouseSnapshotConfigurationRepository repository;
    @Autowired
    private ApplicationEventService applicationEventService;
    @Autowired
    private AuctionItemNativeRepository auctionItemNativeRepository;
    @Autowired
    private DailyAuctionSnapshotNativeRepository dailyAuctionSnapshotNativeRepository;

    /**
     * delete old snapshot statistics from mysql
     *
     * @Scheduled Every Hour
     */
    @Scheduled(cron = "0 0 * * * *")
    public void deleteCurrentStatistics() {
        repository.findAll()
                .stream()
                .forEach(x -> auctionsExporter.deleteOldSnapshotsFor(x.getRealm()));
    }

    /**
     * Delete snapshots from the elasticsearch
     *
     * @Scheduled Every day at 2 oclock
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void deleteOldSnapshotData() {
        applicationEventService.create(ApplicationEventTypeEnum.JOB_STARTED, "starting deletion of old auction data");
        LocalDate localDate = LocalDate.now().minusDays(31);
        auctionItemNativeRepository
                .deleteBeforeDate(localDate.toEpochDay());
        applicationEventService.create(ApplicationEventTypeEnum.JOB_ENDED, "done deletion of old auction data");
    }

    /**
     * Delete daily data more than 60 days old.
     *
     * @Scheduled Every day at 8 oclock
     */
    @Scheduled(cron = "0 0 8 * * ?")
    public void deleteOldStatistics() {
        dailyAuctionSnapshotNativeRepository.deleteEntriesBefore(
                LocalDate.now().minusDays(59)
        );
    }

}
