package com.deswaef.wowscrappie.raids.service;

import com.deswaef.wowscrappie.raids.domain.Boss;
import com.deswaef.wowscrappie.raids.domain.Raid;
import com.deswaef.wowscrappie.raids.domain.RaidBoss;

import java.util.List;
import java.util.Optional;

public interface BossService {
    List<RaidBoss> findByRaid(Raid raid);

    List<RaidBoss> findAllRaidBosses();

    Optional<RaidBoss> findById(long id);
    Optional<Boss> findByWarcraftlogsEncounterId(Long wclEncounterId);
}
