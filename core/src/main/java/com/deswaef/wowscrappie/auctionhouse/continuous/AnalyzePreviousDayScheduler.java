package com.deswaef.wowscrappie.auctionhouse.continuous;

import com.deswaef.wowscrappie.applicationevent.ApplicationEventTypeEnum;
import com.deswaef.wowscrappie.applicationevent.service.ApplicationEventService;
import com.deswaef.wowscrappie.auctionhouse.analyzer.DailyAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AnalyzePreviousDayScheduler {

    @Autowired
    private DailyAnalyzer analyzer;

    @Autowired
    private ApplicationEventService applicationEventService;

    @Scheduled(cron = "0 8 17 * * ?")
    public void analyzeForPreviousDay() {
        applicationEventService.create(ApplicationEventTypeEnum.JOB_STARTED, "starting to analyze the previous day");
        analyzer.analyzeForDay(LocalDate.now().minusDays(1));
        applicationEventService.create(ApplicationEventTypeEnum.JOB_ENDED, "done analyzing the previous day");
    }

}
