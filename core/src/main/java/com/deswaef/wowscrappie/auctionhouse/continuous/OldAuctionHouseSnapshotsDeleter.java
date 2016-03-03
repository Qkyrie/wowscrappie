package com.deswaef.wowscrappie.auctionhouse.continuous;

import com.deswaef.wowscrappie.auctionhouse.exporter.AuctionsExporter;
import com.deswaef.wowscrappie.auctionhouse.repository.AuctionHouseSnapshotConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "com.deswaef.wowscrappie.jobs.oldsnapshotdeleter.enabled", havingValue = "true")
public class OldAuctionHouseSnapshotsDeleter {

    @Autowired
    private AuctionsExporter auctionsExporter;
    @Autowired
    private AuctionHouseSnapshotConfigurationRepository repository;

    @Scheduled(fixedDelay = 60_000)
    public void schedule() {
        repository.findAll()
                .stream()
                .forEach(x -> auctionsExporter.deleteOldSnapshotsFor(x.getRealm()));
    }

}
