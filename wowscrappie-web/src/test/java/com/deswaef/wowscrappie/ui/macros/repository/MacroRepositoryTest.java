package com.deswaef.wowscrappie.ui.macros.repository;

import com.deswaef.wowscrappie.repository.RepositoryIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.fest.assertions.Assertions.assertThat;

public class MacroRepositoryTest extends RepositoryIntegrationTest {

    @Autowired
    private MacroRepository macroRepository;

    @Test
    public void autowireable() {
        assertThat(macroRepository).isNotNull();
    }

}