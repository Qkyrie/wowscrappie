package com.deswaef.wowscrappie.ui.macros.repository;

import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;
import com.deswaef.wowscrappie.ui.macros.domain.Macro;
import com.deswaef.wowscrappie.ui.macros.domain.MacroConfigRating;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MacroConfigRatingRepository extends JpaRepository<MacroConfigRating, Long> {
    Optional<MacroConfigRating> findByMacro(@Param("macro") Macro macro);
}
