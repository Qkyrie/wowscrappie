package com.deswaef.wowscrappie.auctionhouse.continuous;

import com.deswaef.wowscrappie.applicationevent.ApplicationEventTypeEnum;
import com.deswaef.wowscrappie.applicationevent.service.ApplicationEventService;
import com.deswaef.wowscrappie.auctionhouse.analyzer.DailyAnalyzer;
import com.deswaef.wowscrappie.auctionhouse.repository.AuctionItemNativeRepository;
import com.deswaef.wowscrappie.auctionhouse.service.AuctionHouseSnapshotConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class AnalyzePreviousDayScheduler {

    @Autowired
    private DailyAnalyzer analyzer;

    @Autowired
    private ApplicationEventService applicationEventService;
    @Autowired
    private AuctionHouseSnapshotConfigurationService auctionHouseSnapshotConfigurationService;
    @Autowired
    private AuctionItemNativeRepository auctionItemNativeRepository;


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

    @Scheduled(cron = "0 0 2 * * ?")
    public void deleteOldDailyData() {
        applicationEventService.create(ApplicationEventTypeEnum.JOB_STARTED, "starting deletion of old auction data");
        LocalDate localDate = LocalDate.now().minusDays(31);
        auctionItemNativeRepository
                .deleteBeforeDate(localDate.toEpochDay());
        applicationEventService.create(ApplicationEventTypeEnum.JOB_ENDED, "done deletion of old auction data");
    }

}
