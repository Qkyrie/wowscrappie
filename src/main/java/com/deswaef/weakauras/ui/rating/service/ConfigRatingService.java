package com.deswaef.weakauras.ui.rating.service;

import com.deswaef.weakauras.ui.macros.domain.Macro;
import com.deswaef.weakauras.ui.macros.domain.MacroConfigRating;
import com.deswaef.weakauras.ui.rating.domain.Rating;
import com.deswaef.weakauras.ui.tellmewhen.domain.TellMeWhen;
import com.deswaef.weakauras.ui.tellmewhen.domain.TellMeWhenConfigRating;
import com.deswaef.weakauras.ui.weakauras.domain.WeakAura;
import com.deswaef.weakauras.ui.weakauras.domain.WeakAuraConfigRating;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;

import java.util.Optional;

public interface ConfigRatingService {
    void createTMWRating(TellMeWhen tellMeWhen);
    void createWARating(WeakAura weakAura);
    void createMacroRating(Macro macro);

    Optional<TellMeWhenConfigRating> findByTellMeWhen(Long tellMeWhen);
    Optional<MacroConfigRating> findByMacro(Long macro);
    Optional<WeakAuraConfigRating> findByWeakAura(Long weakaura);

    Rating getPersonalRatingWA(Long weakAura, ScrappieUser user);
    Rating getPersonalRatingTMW(Long tmw, ScrappieUser user);
    Rating getPersonalRatingMacro(Long weakaura, ScrappieUser user);

    void voteTMW(Long tmw, Rating rating, ScrappieUser user);
    void voteWA(Long wa, Rating rating, ScrappieUser user);
    void voteMacro(Long macro, Rating rating, ScrappieUser user);

}
