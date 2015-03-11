package com.deswaef.weakauras.raids.service;

import com.deswaef.weakauras.raids.domain.Raid;

import java.util.Optional;

public interface RaidService {
    Optional<Raid> findById(Long id);
}
