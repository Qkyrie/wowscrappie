package com.deswaef.weakauras.ui.image;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ImageCleanupScheduler {

    private Log logger = LogFactory.getLog(ImageCleanupScheduler.class);

    public static final long HOURS = 2L;
    public static final long MINUTES = 60L;
    public static final long SECONDS = 60L;
    private final static Long DELETION_DELAY = HOURS * MINUTES * SECONDS;

    @Autowired
    private ImageStore imageStore;

    @Scheduled(fixedDelay = 7200000)
    public void cleanupOldImages() {
        imageStore.deleteOldEntries();
    }

}
