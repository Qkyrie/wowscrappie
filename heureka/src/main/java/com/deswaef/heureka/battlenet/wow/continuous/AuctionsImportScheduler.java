package com.deswaef.heureka.battlenet.wow.continuous;

import com.deswaef.wowscrappie.auctionhouse.domain.AuctionHouseSnapshotConfiguration;
import com.deswaef.wowscrappie.auctionhouse.service.AuctionHouseSnapshotConfigurationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import rx.Observable;

import javax.annotation.PostConstruct;


@Component
@Profile("!integrationtest")
public class AuctionsImportScheduler {

    private Log LOG = LogFactory.getLog(this.getClass());

    @Autowired
    private AuctionHouseSnapshotConfigurationService auctionHouseSnapshotConfigurationService;
    @Autowired
    private BattlenetAuctionsImporter importer;

    @Scheduled(fixedDelay = 60_000)
    public void importJob() {
        LOG.debug("Starting importJob");
        findConfigurationsThatNeedUpdates()
                .forEach(
                        element -> {
                            LOG.debug(String.format("starting for configuration %s", element));
                            importer.importAuctions(element.getRealm().getId());
                            LOG.debug(String.format("started running for configuration %s", element));
                        }
                );
        LOG.debug("End of ImportJob");
    }


    private Observable<AuctionHouseSnapshotConfiguration> findConfigurationsThatNeedUpdates() {
        return auctionHouseSnapshotConfigurationService.findUpdatable();
    }


}
