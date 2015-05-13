package com.deswaef.weakauras.raids.service;

import com.deswaef.weakauras.raids.domain.Boss;
import com.deswaef.weakauras.raids.domain.Raid;
import com.deswaef.weakauras.raids.domain.RaidBoss;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface BossService {
    List<RaidBoss> findByRaid(Raid raid);

    List<RaidBoss> findAllRaidBosses();

    Optional<RaidBoss> findById(long id);
    Optional<Boss> findByWarcraftlogsEncounterId(Long wclEncounterId);
}
