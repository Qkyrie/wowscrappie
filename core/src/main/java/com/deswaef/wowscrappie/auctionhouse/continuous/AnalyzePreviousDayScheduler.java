package com.deswaef.wowscrappie.auctionhouse.continuous;

import com.deswaef.wowscrappie.applicationevent.ApplicationEventTypeEnum;
import com.deswaef.wowscrappie.applicationevent.service.ApplicationEventService;
import com.deswaef.wowscrappie.auctionhouse.analyzer.DailyAnalyzer;
import com.deswaef.wowscrappie.auctionhouse.service.AuctionHouseSnapshotConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@ConditionalOnProperty(value = "com.deswaef.wowscrappie.jobs.previousdayanalyzer.enabled", havingValue = "true")
public class AnalyzePreviousDayScheduler {

    @Autowired
    private DailyAnalyzer analyzer;

    @Autowired
    private ApplicationEventService applicationEventService;
    @Autowired
    private AuctionHouseSnapshotConfigurationService auctionHouseSnapshotConfigurationService;


    @Scheduled(cron = "0 0 1 * * ?")
    public void analyzeForPreviousDay() {
        analyzeForAPreviousDay(1);
    }

    public void analyzeForAPreviousDay(int amount) {
        applicationEventService.create(ApplicationEventTypeEnum.JOB_STARTED, "starting to analyze the previous day");

        auctionHouseSnapshotConfigurationService
                .findAll()
                .subscribe(
                        config -> {
                            analyzer.analyzeForDay(LocalDate.now().minusDays(amount), config.getRealm().getId());
                        }
                );
        applicationEventService.create(ApplicationEventTypeEnum.JOB_ENDED, "done analyzing the previous day");
    }


}
