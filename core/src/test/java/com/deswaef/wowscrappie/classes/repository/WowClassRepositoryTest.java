package com.deswaef.wowscrappie.classes.repository;

import com.deswaef.wowscrappie.classes.domain.WowClass;
import com.deswaef.wowscrappie.classes.domain.WowClassFixture;
import com.deswaef.wowscrappie.repository.RepositoryIntegrationTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
public class WowClassRepositoryTest extends RepositoryIntegrationTest {

    private static final WowClass DK = WowClassFixture.dk();
    @Autowired
    private WowClassRepository wowClassRepository;

    @Before
    public void setUp() throws Exception {
        wowClassRepository.deleteAllInBatch();
        wowClassRepository.save(DK);
    }

    @Test
    public void findBySlug() throws Exception {
        assertThat(wowClassRepository.findBySlug(DK.getSlug())).isPresent();
    }
}