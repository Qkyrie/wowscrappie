package com.deswaef.wowscrappie.raids.service;

import com.deswaef.wowscrappie.raids.domain.Raid;

import java.util.List;
import java.util.Optional;

public interface RaidService {
    Optional<Raid> findById(Long id);
    List<Raid> findAll();
}
