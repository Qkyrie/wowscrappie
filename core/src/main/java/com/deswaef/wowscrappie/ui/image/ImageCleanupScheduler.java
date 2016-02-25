package com.deswaef.wowscrappie.ui.image;

import com.deswaef.wowscrappie.applicationevent.ApplicationEventTypeEnum;
import com.deswaef.wowscrappie.applicationevent.service.ApplicationEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ImageCleanupScheduler {

    @Autowired
    private ApplicationEventService applicationEventService;

    @Autowired
    private ImageStore imageStore;

    @Scheduled(fixedDelay = 7200000)
    public void cleanupOldImages() {
        applicationEventService.create(
                ApplicationEventTypeEnum.JOB_STARTED,
                "cleanup of old images started"
        );
        imageStore.deleteOldEntries();
        applicationEventService.create(
                ApplicationEventTypeEnum.JOB_ENDED,
                "cleanup of old images ended"
        );
    }

}
