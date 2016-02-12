package com.deswaef.wowscrappie.ui.tellmewhen.repository;

import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;
import com.deswaef.wowscrappie.raids.domain.Boss;
import com.deswaef.wowscrappie.ui.tellmewhen.domain.BossFightTellMeWhen;
import com.deswaef.wowscrappie.ui.tellmewhen.domain.TellMeWhen;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BossFightTellMeWhenRepository extends JpaRepository<BossFightTellMeWhen, Long>{
    List<TellMeWhen> findByBoss(@Param("boss") Boss boss);

    @Query("select tmw from BossFightTellMeWhen tmw where tmw.boss = :boss and approved = true")
    List<TellMeWhen> findByBossAndApproved(@Param("boss") Boss boss);

    @Query("select count(tmw) from BossFightTellMeWhen tmw where tmw.boss = :boss")
    Long countByBoss(@Param("boss") Boss boss);

    @Query("select count(tmw) from BossFightTellMeWhen tmw where tmw.boss = :boss and approved = true")
    Long countByBossAndApproved(@Param("boss") Boss boss);
}
