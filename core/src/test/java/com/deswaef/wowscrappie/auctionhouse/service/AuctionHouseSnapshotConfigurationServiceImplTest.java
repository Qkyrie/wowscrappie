package com.deswaef.wowscrappie.auctionhouse.service;

import com.deswaef.wowscrappie.auctionhouse.domain.AuctionHouseSnapshotConfiguration;
import com.deswaef.wowscrappie.auctionhouse.repository.AuctionHouseSnapshotConfigurationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuctionHouseSnapshotConfigurationServiceImplTest {

    @InjectMocks
    private AuctionHouseSnapshotConfigurationServiceImpl auctionHouseSnapshotConfigurationService;
    @Mock
    private AuctionHouseSnapshotConfigurationRepository repository;

    @Test
    public void findallCallsRepository() throws Exception {
        AuctionHouseSnapshotConfiguration mockedConfiguration = mock(AuctionHouseSnapshotConfiguration.class);
        List<AuctionHouseSnapshotConfiguration> asList = singletonList(mockedConfiguration);
        when(repository.findAll())
                .thenReturn(asList);

        assertThat(
                auctionHouseSnapshotConfigurationService.findAll()
                        .toList().toBlocking().first()
        ).isEqualTo(asList);

    }
}