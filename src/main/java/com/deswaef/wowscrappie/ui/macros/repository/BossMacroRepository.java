package com.deswaef.wowscrappie.ui.macros.repository;

import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;
import com.deswaef.wowscrappie.raids.domain.Boss;
import com.deswaef.wowscrappie.ui.macros.domain.BossFightMacro;
import com.deswaef.wowscrappie.ui.macros.domain.Macro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BossMacroRepository extends JpaRepository<BossFightMacro, Long> {
    List<Macro> findByBoss(@Param("boss") Boss boss);

    @Query("select sm from BossFightMacro sm where boss = :boss and approved = true ")
    List<Macro> findByBossAndApproved(@Param("boss") Boss boss);

    @Query("select count(sm) from BossFightMacro sm where boss = :boss and approved = true ")
    Long countByBossAndApproved(@Param("boss") Boss boss);

    @Query("select count(sm) from BossFightMacro sm where boss = :boss")
    Long countByBoss(@Param("boss") Boss boss);
}
