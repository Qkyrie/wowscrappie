package com.deswaef.weakauras.ui.tellmewhen.repository;

import com.deswaef.weakauras.infrastructure.repository.JpaRepository;
import com.deswaef.weakauras.ui.tellmewhen.domain.TellMeWhen;
import com.deswaef.weakauras.ui.tellmewhen.domain.TellMeWhenConfigRating;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TellMeWhenConfigRatingRepository extends JpaRepository<TellMeWhenConfigRating, Long>{
    Optional<TellMeWhenConfigRating> findByTellMeWhen(@Param("tellMeWhen") TellMeWhen tellMeWhen);
}
