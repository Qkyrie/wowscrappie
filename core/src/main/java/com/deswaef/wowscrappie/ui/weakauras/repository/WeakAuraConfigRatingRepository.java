package com.deswaef.wowscrappie.ui.weakauras.repository;

import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;
import com.deswaef.wowscrappie.ui.weakauras.domain.WeakAura;
import com.deswaef.wowscrappie.ui.weakauras.domain.WeakAuraConfigRating;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WeakAuraConfigRatingRepository extends JpaRepository<WeakAuraConfigRating, Long> {
    Optional<WeakAuraConfigRating> findByWeakAura(@Param("weakAura") WeakAura weakAura);
}
