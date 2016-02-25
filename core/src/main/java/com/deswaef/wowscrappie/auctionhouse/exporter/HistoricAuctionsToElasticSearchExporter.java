package com.deswaef.wowscrappie.auctionhouse.exporter;

import com.deswaef.wowscrappie.applicationevent.ApplicationEventTypeEnum;
import com.deswaef.wowscrappie.applicationevent.service.ApplicationEventService;
import com.deswaef.wowscrappie.auctionhouse.domain.AuctionHouseSnapshot;
import com.deswaef.wowscrappie.auctionhouse.domain.HistoricAuctionHouseSnapshot;
import com.deswaef.wowscrappie.auctionhouse.repository.AuctionHouseSnapshotRepository;
import com.deswaef.wowscrappie.auctionhouse.repository.HistoricAuctionHouseSnapshotRepository;
import com.deswaef.wowscrappie.realm.domain.Realm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class HistoricAuctionsToElasticSearchExporter implements AuctionsExporter {

    @Autowired
    private AuctionHouseSnapshotRepository auctionHouseSnapshotRepository;
    @Autowired
    private HistoricAuctionHouseSnapshotRepository historicAuctionHouseSnapshotRepository;
    @Autowired
    private ApplicationEventService applicationEventService;

    @Override
    @Transactional
    public void exportFor(Realm realm) {
        List<Date> distinctExportTimeByRealm = auctionHouseSnapshotRepository.findDistinctExportTimeByRealm(realm);
        if (distinctExportTimeByRealm != null && !distinctExportTimeByRealm.isEmpty() && distinctExportTimeByRealm.size() > 1) {
            applicationEventService.create(
                    ApplicationEventTypeEnum.JOB_STARTED, "historic auctions exporter for " + realm.getLocality() + "-" + realm.getName() + " started"
            );
            exportOldDates(distinctExportTimeByRealm, realm);
            applicationEventService.create(
                    ApplicationEventTypeEnum.JOB_ENDED, "historic auctions exporter for " + realm.getLocality() + "-" + realm.getName() + " ended"
            );
        }
    }

    private void exportOldDates(List<Date> dates, Realm realm) {
        dates
                .stream()
                .filter(dateElement -> !dateElement.equals(getFirstDate(dates.stream())))
                .forEach(element -> exportForOldDate(element, realm));
    }

    private void exportForOldDate(Date element, Realm realm) {
        List<AuctionHouseSnapshot> auctionsnapshots = auctionHouseSnapshotRepository.findByExportTimeBeforeAndRealm(element, realm);
        if (!auctionsnapshots.isEmpty()) {
            historicAuctionHouseSnapshotRepository.save(auctionsnapshots
                    .stream()
                    .map(HistoricAuctionHouseSnapshot::from)
                    .collect(Collectors.toList())
            );
        }
        auctionHouseSnapshotRepository.delete(auctionsnapshots);
    }

    protected Date getFirstDate(Stream<Date> allDates) {
        return allDates
                .sorted()
                .findFirst().get();
    }

}
