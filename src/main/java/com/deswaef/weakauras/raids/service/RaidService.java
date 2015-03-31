package com.deswaef.weakauras.raids.service;

import com.deswaef.weakauras.raids.domain.Raid;
import com.deswaef.weakauras.raids.domain.RaidBoss;

import java.util.List;
import java.util.Optional;

public interface RaidService {
    Optional<Raid> findById(Long id);
    List<Raid> findAll();
}
