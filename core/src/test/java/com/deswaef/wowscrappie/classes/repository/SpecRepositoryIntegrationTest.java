package com.deswaef.wowscrappie.classes.repository;

import com.deswaef.wowscrappie.classes.domain.Spec;
import com.deswaef.wowscrappie.classes.domain.SpecFixture;
import com.deswaef.wowscrappie.classes.domain.WowClass;
import com.deswaef.wowscrappie.classes.domain.WowClassFixture;
import com.deswaef.wowscrappie.repository.RepositoryIntegrationTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class SpecRepositoryIntegrationTest extends RepositoryIntegrationTest {

    @Autowired
    private WowClassRepository wowClassRepository;
    @Autowired
    private SpecRepository specRepository;

    @Before
    public void setUp() throws Exception {
        specRepository.deleteAll();
        wowClassRepository.deleteAll();

        wowClassRepository.save(WowClassFixture.dk());
        specRepository.save(SpecFixture.frost_dk());
    }

    @Test
    public void findOneFindsSpec() throws Exception {
        Optional<Spec> spec = specRepository.findOne(SpecFixture.frost_dk().getId());
        assertThat(spec.isPresent()).isTrue();

        assertThat(spec.get().getName()).isEqualTo("Frost");
    }
}