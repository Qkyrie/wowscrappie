package com.deswaef.wowscrappie.raids.repository;

import com.deswaef.wowscrappie.repository.RepositoryIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.fest.assertions.Assertions.assertThat;

public class RaidRepositoryTest extends RepositoryIntegrationTest {

    @Autowired
    private RaidRepository raidRepository;

    @Test
    public void autowireable() {
        assertThat(raidRepository).isNotNull();
    }

}