package com.deswaef.wowscrappie.raids.repository;

import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;
import com.deswaef.wowscrappie.raids.domain.Raid;

public interface RaidRepository extends JpaRepository<Raid, Long> {
}
