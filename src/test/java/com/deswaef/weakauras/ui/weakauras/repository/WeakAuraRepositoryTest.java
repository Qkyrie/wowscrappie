package com.deswaef.weakauras.ui.weakauras.repository;

import com.deswaef.weakauras.repository.RepositoryIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.fest.assertions.Assertions.assertThat;

public class WeakAuraRepositoryTest extends RepositoryIntegrationTest {

    @Autowired
    private WeakAuraRepository weakAuraRepository;

    @Test
    public void autowireable() {
        assertThat(weakAuraRepository).isNotNull();
    }

}