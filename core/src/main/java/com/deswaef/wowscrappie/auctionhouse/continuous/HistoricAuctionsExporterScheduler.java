package com.deswaef.wowscrappie.auctionhouse.continuous;

import com.deswaef.wowscrappie.auctionhouse.exporter.AuctionsExporter;
import com.deswaef.wowscrappie.auctionhouse.repository.AuctionHouseSnapshotConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HistoricAuctionsExporterScheduler {

    @Autowired
    private AuctionsExporter auctionsExporter;
    @Autowired
    private AuctionHouseSnapshotConfigurationRepository repository;

    @Scheduled(fixedDelay = 60_000)
    public void schedule() {
        repository.findAll()
                .stream()
                .forEach(x -> auctionsExporter.exportFor(x.getRealm()));
    }

}
