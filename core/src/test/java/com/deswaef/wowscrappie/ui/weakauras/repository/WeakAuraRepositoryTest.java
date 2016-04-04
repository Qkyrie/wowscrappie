package com.deswaef.wowscrappie.ui.weakauras.repository;

import com.deswaef.wowscrappie.repository.RepositoryIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;


public class WeakAuraRepositoryTest extends RepositoryIntegrationTest {

    @Autowired
    private WeakAuraRepository weakAuraRepository;

    @Test
    public void autowireable() {
        assertThat(weakAuraRepository).isNotNull();
    }

}