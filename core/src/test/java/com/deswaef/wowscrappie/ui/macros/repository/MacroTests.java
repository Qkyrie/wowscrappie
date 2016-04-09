package com.deswaef.wowscrappie.ui.macros.repository;

import com.deswaef.wowscrappie.repository.IntegrationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;


public class MacroTests extends IntegrationTests {

    @Autowired
    private MacroRepository macroRepository;

    @Test
    public void autowireable() {
        assertThat(macroRepository).isNotNull();
    }

}