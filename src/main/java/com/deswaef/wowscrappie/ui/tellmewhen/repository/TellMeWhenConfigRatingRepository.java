package com.deswaef.wowscrappie.ui.tellmewhen.repository;

import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;
import com.deswaef.wowscrappie.ui.tellmewhen.domain.TellMeWhen;
import com.deswaef.wowscrappie.ui.tellmewhen.domain.TellMeWhenConfigRating;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TellMeWhenConfigRatingRepository extends JpaRepository<TellMeWhenConfigRating, Long>{
    Optional<TellMeWhenConfigRating> findByTellMeWhen(@Param("tellMeWhen") TellMeWhen tellMeWhen);
}
