package com.deswaef.wowscrappie.raids.repository;

import com.deswaef.wowscrappie.raids.domain.Boss;
import com.deswaef.wowscrappie.raids.domain.WorldBoss;
import com.deswaef.wowscrappie.repository.IntegrationTests;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class BossTests extends IntegrationTests {

    private static final long RUKHMAR_ID = 1L;
    private static final String RUKHMAR = "Rukhmar";
    @Autowired
    private BossRepository bossRepository;

    @Before
    public void setUp() {
        Boss rukhmar = new WorldBoss()
                .setId(RUKHMAR_ID)
                .setName(RUKHMAR)
                .setSlug("rukhmar");
        bossRepository.deleteAll();
        bossRepository.save(rukhmar);
    }

    @Test
    public void autowireable() {
        assertThat(bossRepository).isNotNull();
    }

    @Test
    @Transactional
    public void rukhmarExists() {
        Optional<Boss> optionalRukhmar = bossRepository.findOne(RUKHMAR_ID);
        assertThat(optionalRukhmar.isPresent()).isTrue();
        assertThat(optionalRukhmar.get().getName()).isEqualTo(RUKHMAR);
        assertThat(optionalRukhmar.get().getBossFightWeakAura()).isEmpty();
    }

}