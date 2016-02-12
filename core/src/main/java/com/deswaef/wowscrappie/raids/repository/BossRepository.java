package com.deswaef.wowscrappie.raids.repository;

import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;
import com.deswaef.wowscrappie.raids.domain.Boss;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BossRepository extends JpaRepository<Boss, Long> {
    Optional<Boss> findByWarcraftlogsEncounterId(@Param("warcraftlogsEncounterId") Long warcraftlogsEncounterId);
}
