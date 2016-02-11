package com.deswaef.wowscrappie.raids.service;

import com.deswaef.wowscrappie.raids.domain.Raid;
import com.deswaef.wowscrappie.raids.domain.RaidBoss;
import com.deswaef.wowscrappie.raids.repository.RaidBossRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BossServiceImplTest {

    public static final long RAid_BOSS_ID = 1L;
    @InjectMocks
    private BossServiceImpl bossService;

    @Mock
    private RaidBossRepository raidBossRepository;
    @Mock
    private Raid raid;
    @Mock
    private RaidBoss raidBoss;


    @Test
    public void findByRaidIdCallsRepository() {
        when(raidBossRepository.findByRaid(raid))
                .thenReturn(Arrays.asList(raidBoss));
        assertThat(bossService.findByRaid(raid))
                .contains(raidBoss);
    }

    @Test
    public void findByIdAndNotExists() {
        when(raidBossRepository.findOne(RAid_BOSS_ID))
                .thenReturn(Optional.empty());
        assertThat(bossService.findById(RAid_BOSS_ID).isPresent())
                .isFalse();
    }

    @Test
    public void findByIdAndExists(){
        when(raidBossRepository.findOne(RAid_BOSS_ID))
                .thenReturn(Optional.of(raidBoss));
        assertThat(bossService.findById(RAid_BOSS_ID).get())
                .isEqualTo(raidBoss);
    }

}