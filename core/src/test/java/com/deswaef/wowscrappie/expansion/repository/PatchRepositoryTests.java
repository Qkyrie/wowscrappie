package com.deswaef.wowscrappie.expansion.repository;

import com.deswaef.wowscrappie.expansion.domain.Expansion;
import com.deswaef.wowscrappie.expansion.domain.ExpansionFixture;
import com.deswaef.wowscrappie.expansion.domain.Patch;
import com.deswaef.wowscrappie.expansion.domain.PatchFixture;
import com.deswaef.wowscrappie.repository.IntegrationTests;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class PatchRepositoryTests extends IntegrationTests {

    private static final Expansion VANILLA = ExpansionFixture.vanilla();
    private static final Patch FIRST_PATCH = PatchFixture.fistPatch();

    @Autowired
    private PatchRepository patchRepository;
    @Autowired
    private ExpansionRepository expansionRepository;

    @Before
    public void setUp() throws Exception {
        patchRepository.deleteAllInBatch();
        expansionRepository.deleteAllInBatch();
    }

    @Test
    public void getPatch() throws Exception {
        expansionRepository.save(VANILLA);
        patchRepository.save(FIRST_PATCH);

        Optional<Patch> one = patchRepository.findOne(FIRST_PATCH.getId());
        assertThat(one).isPresent();

        assertThat(one.get().getExpansion().getName())
                .isEqualTo(VANILLA.getName());
    }
}