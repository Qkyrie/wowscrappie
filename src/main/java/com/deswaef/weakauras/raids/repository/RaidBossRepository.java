package com.deswaef.weakauras.raids.repository;

import com.deswaef.weakauras.infrastructure.repository.JpaRepository;
import com.deswaef.weakauras.raids.domain.Raid;
import com.deswaef.weakauras.raids.domain.RaidBoss;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RaidBossRepository extends JpaRepository<RaidBoss, Long>{
    List<RaidBoss> findByRaid(@Param("raid") Raid raid);
}
