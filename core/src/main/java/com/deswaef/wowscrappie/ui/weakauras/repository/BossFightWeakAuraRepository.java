package com.deswaef.wowscrappie.ui.weakauras.repository;

import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;
import com.deswaef.wowscrappie.raids.domain.Boss;
import com.deswaef.wowscrappie.ui.weakauras.domain.BossFightWeakAura;
import com.deswaef.wowscrappie.ui.weakauras.domain.WeakAura;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BossFightWeakAuraRepository extends JpaRepository<BossFightWeakAura, Long> {
    List<WeakAura> findByBoss(@Param("boss") Boss boss);

    @Query("select wa from BossFightWeakAura wa where wa.boss = :boss and approved = true")
    List<WeakAura> findByBossAndApproved(@Param("boss") Boss boss);

    @Query("select count(wa) from BossFightWeakAura wa where wa.boss = :boss and approved = true")
    Long countByBossAndApproved(@Param("boss") Boss boss);

    @Query("select count(wa) from BossFightWeakAura wa where wa.boss = :boss")
    Long countByBoss(@Param("boss") Boss boss);
}
