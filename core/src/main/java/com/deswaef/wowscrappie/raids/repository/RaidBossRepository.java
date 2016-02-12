package com.deswaef.wowscrappie.raids.repository;

import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;
import com.deswaef.wowscrappie.raids.domain.Raid;
import com.deswaef.wowscrappie.raids.domain.RaidBoss;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RaidBossRepository extends JpaRepository<RaidBoss, Long>{
    List<RaidBoss> findByRaid(@Param("raid") Raid raid);
}
