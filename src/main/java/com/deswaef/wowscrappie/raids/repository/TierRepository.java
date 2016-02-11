package com.deswaef.wowscrappie.raids.repository;

import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;
import com.deswaef.wowscrappie.raids.domain.Tier;

public interface TierRepository extends JpaRepository<Tier, Long> {
}
