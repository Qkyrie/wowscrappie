package com.deswaef.weakauras.raids.repository;

import com.deswaef.weakauras.infrastructure.repository.JpaRepository;
import com.deswaef.weakauras.raids.domain.Boss;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BossRepository extends JpaRepository<Boss, Long> {
    Optional<Boss> findByWarcraftlogsEncounterId(@Param("warcraftlogsEncounterId") Long warcraftlogsEncounterId);
}
