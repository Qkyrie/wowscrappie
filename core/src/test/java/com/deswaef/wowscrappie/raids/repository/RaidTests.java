package com.deswaef.wowscrappie.raids.repository;

import com.deswaef.wowscrappie.repository.IntegrationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class RaidTests extends IntegrationTests {

    @Autowired
    private RaidRepository raidRepository;

    @Test
    public void autowireable() {
        assertThat(raidRepository).isNotNull();
    }

}