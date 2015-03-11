package com.deswaef.weakauras.ui.weakauras.repository;

import com.deswaef.weakauras.infrastructure.repository.JpaRepository;
import com.deswaef.weakauras.ui.weakauras.domain.WeakAura;
import com.deswaef.weakauras.ui.weakauras.domain.WeakAuraConfigRating;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WeakAuraConfigRatingRepository extends JpaRepository<WeakAuraConfigRating, Long> {
    Optional<WeakAuraConfigRating> findByWeakAura(@Param("weakAura") WeakAura weakAura);
}
