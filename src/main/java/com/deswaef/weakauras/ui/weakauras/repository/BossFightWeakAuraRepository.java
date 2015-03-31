package com.deswaef.weakauras.ui.weakauras.repository;

import com.deswaef.weakauras.infrastructure.repository.JpaRepository;
import com.deswaef.weakauras.raids.domain.Boss;
import com.deswaef.weakauras.ui.weakauras.domain.BossFightWeakAura;
import com.deswaef.weakauras.ui.weakauras.domain.WeakAura;
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
