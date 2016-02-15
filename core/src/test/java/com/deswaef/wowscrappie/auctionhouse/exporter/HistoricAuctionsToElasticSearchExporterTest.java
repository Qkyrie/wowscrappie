package com.deswaef.wowscrappie.auctionhouse.exporter;

import com.deswaef.wowscrappie.auctionhouse.repository.AuctionHouseSnapshotRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class HistoricAuctionsToElasticSearchExporterTest {

    @InjectMocks
    private HistoricAuctionsToElasticSearchExporter historicAuctionsToElasticSearchExporter;
    @Mock
    private AuctionHouseSnapshotRepository auctionHouseSnapshotRepository;

    @Test
    public void firstDateFetchedCorrectly() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime yesterday = now.minus(1, ChronoUnit.DAYS);
        LocalDateTime tomorrow = now.plus(1, ChronoUnit.DAYS);

        Date firstDate = historicAuctionsToElasticSearchExporter.getFirstDate(Arrays.asList(now, tomorrow, yesterday)
                .stream()
                .map(x -> Date.from(x.atZone(ZoneId.systemDefault()).toInstant())));

        assertThat(LocalDateTime.ofInstant(Instant.ofEpochMilli(firstDate.getTime()), ZoneId.systemDefault()))
                .isEqualTo(yesterday);

    }
}