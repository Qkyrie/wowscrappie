package com.deswaef.weakauras.raids.service;

import com.deswaef.weakauras.raids.domain.Raid;
import com.deswaef.weakauras.raids.domain.RaidBoss;

import java.util.List;
import java.util.Optional;

public interface BossService {
    List<RaidBoss> findByRaid(Raid raid);

    Optional<RaidBoss> findById(long id);
}
