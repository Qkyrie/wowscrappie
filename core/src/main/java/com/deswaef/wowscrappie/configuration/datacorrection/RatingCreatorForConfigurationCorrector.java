package com.deswaef.wowscrappie.configuration.datacorrection;

import com.deswaef.wowscrappie.ui.macros.service.MacroService;
import com.deswaef.wowscrappie.ui.rating.service.ConfigRatingService;
import com.deswaef.wowscrappie.ui.tellmewhen.service.TellMeWhenService;
import com.deswaef.wowscrappie.ui.weakauras.service.WeakAuraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * for each configuration that does not yet have a rating, add one
 */
@Component
public class RatingCreatorForConfigurationCorrector implements DataCorrection {

    @Autowired
    private TellMeWhenService tellMeWhenService;
    @Autowired
    private MacroService macroService;
    @Autowired
    private WeakAuraService weakAuraService;
    @Autowired
    private ConfigRatingService configRatingService;

    @Override
    @Transactional
    public void run() {
        weakAuraService.findAll()
                .stream()
                .forEach(configRatingService::createWARating);

        tellMeWhenService.findAll()
                .stream()
                .forEach(configRatingService::createTMWRating);

        macroService.findAll()
                .stream()
                .forEach(configRatingService::createMacroRating);
    }
}
