package com.deswaef.wowscrappie.classes.repository;

import com.deswaef.wowscrappie.classes.domain.Spec;
import com.deswaef.wowscrappie.classes.domain.SpecFixture;
import com.deswaef.wowscrappie.classes.domain.WowClass;
import com.deswaef.wowscrappie.classes.domain.WowClassFixture;
import com.deswaef.wowscrappie.repository.RepositoryIntegrationTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@Transactional
public class SpecRepositoryIntegrationTest extends RepositoryIntegrationTest {

    public static final WowClass DK = WowClassFixture.dk();
    public static final Spec FROST_DK = SpecFixture.frost_dk();
    @Autowired
    private WowClassRepository wowClassRepository;
    @Autowired
    private SpecRepository specRepository;

    @Before
    public void setUp() throws Exception {
        specRepository.deleteAll();
        wowClassRepository.deleteAll();

        wowClassRepository.save(DK);
        specRepository.save(FROST_DK);
    }

    @Test
    public void findOneFindsSpec() throws Exception {
        Optional<Spec> spec = specRepository.findOne(SpecFixture.frost_dk().getId());
        assertThat(spec.isPresent()).isTrue();

        assertThat(spec.get().getName()).isEqualTo(FROST_DK.getName());
    }

    @Test
    public void findBySlugAndWowClass() throws Exception {
        Optional<Spec> frost = specRepository.findBySlugAndWowClass(FROST_DK.getSlug(), wowClassRepository.findOne(DK.getId()).get());
        assertThat(frost.isPresent()).isTrue();
    }

    @Test
    public void findByWarcraftlogsIdAndWowClassWarcraftlogsId() throws Exception {
        Optional<Spec> spec = specRepository.findByWarcraftlogsIdAndWowClassWarcraftlogsId(FROST_DK.getWarcraftlogsId(), DK.getWarcraftlogsId());
        assertThat(spec.isPresent()).isTrue();
        assertThat(spec.get().getName()).isEqualTo(FROST_DK.getName());
    }
}