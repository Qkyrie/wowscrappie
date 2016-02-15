package com.deswaef.wowscrappie.expansion.repository;

import com.deswaef.wowscrappie.expansion.domain.Patch;
import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;

public interface PatchRepository extends JpaRepository<Patch, Long> {
}
