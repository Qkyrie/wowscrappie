package com.deswaef.weakauras.raids.service;


import com.deswaef.weakauras.raids.domain.Raid;
import com.deswaef.weakauras.raids.repository.RaidRepository;
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
public class RaidServiceImplTest {

    @InjectMocks
    private RaidServiceImpl raidService;
    @Mock
    private RaidRepository raidRepository;
    @Mock
    private Raid raid;

    public static final long RAID_ID = 1L;

    @Test
    public void findByIdAndExists() {
        when(raidRepository.findOne(RAID_ID))
                .thenReturn(Optional.of(raid));
        assertThat(raidService.findById(RAID_ID).get())
                .isEqualTo(raid);
    }

    @Test
    public void findByIdAndDoesntExist(){
        when(raidRepository.findOne(RAID_ID))
                .thenReturn(Optional.empty());
        assertThat(raidService.findById(RAID_ID).isPresent())
                .isFalse();
    }

    @Test
    public void findAllCallsRepository() {
        when(raidRepository.findAll())
                .thenReturn(Arrays.asList(raid));
        assertThat(raidService.findAll())
                .contains(raid);
    }

}