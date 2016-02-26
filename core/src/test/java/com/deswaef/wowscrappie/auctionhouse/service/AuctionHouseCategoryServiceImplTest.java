package com.deswaef.wowscrappie.auctionhouse.service;

import com.deswaef.wowscrappie.auctionhouse.domain.AuctionHouseCategory;
import com.deswaef.wowscrappie.auctionhouse.repository.AuctionhouseCategoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuctionHouseCategoryServiceImplTest {

    @InjectMocks
    private AuctionHouseCategoryServiceImpl categoryService;

    @Mock
    private AuctionhouseCategoryRepository auctionhouseCategoryRepository;

    @Test
    public void findAllCallsRepository() throws Exception {
        ArrayList<AuctionHouseCategory> dataBaseValues = new ArrayList<>();
        when(auctionhouseCategoryRepository.findAll())
                .thenReturn(dataBaseValues);

        assertThat(
                categoryService.findAll().toList().toBlocking().single()
        ).isEqualTo(dataBaseValues);
    }
}