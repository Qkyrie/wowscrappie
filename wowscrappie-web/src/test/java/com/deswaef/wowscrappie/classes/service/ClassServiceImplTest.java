package com.deswaef.wowscrappie.classes.service;

import com.deswaef.wowscrappie.classes.domain.WowClass;
import com.deswaef.wowscrappie.classes.repository.WowClassRepository;
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
public class ClassServiceImplTest {

    private static final String DEATH_KNIGHT = "death_knight";
    private static final String UNKNOWN = "unknown";
    @InjectMocks
    private ClassServiceImpl classService;
    @Mock
    private WowClassRepository wowClassRepository;

    @Test
    public void bySlugAndFound() {
        WowClass wowClass = new WowClass();
        when(wowClassRepository.findBySlug(DEATH_KNIGHT))
                .thenReturn(Optional.of(wowClass));
        assertThat(classService.bySlug(DEATH_KNIGHT).get())
                .isEqualTo(wowClass);
    }

    @Test
    public void bySlugAndNotFound() {
        when(wowClassRepository.findBySlug(UNKNOWN))
                .thenReturn(Optional.empty());
        assertThat(classService.bySlug(UNKNOWN).isPresent())
                .isFalse();
    }

    @Test
    public void findAll() {
        WowClass wowClass = new WowClass();
        when(wowClassRepository.findAll())
                .thenReturn(Arrays.asList(wowClass));
        assertThat(classService.findAll())
                .contains(wowClass);
    }
}