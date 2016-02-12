package com.deswaef.wowscrappie.ui.rating.repository;

import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;
import com.deswaef.wowscrappie.ui.rating.domain.RatingByUser;

public interface RatingByUserRepository extends JpaRepository<RatingByUser, Long>{
}
