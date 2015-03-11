package com.deswaef.weakauras.configuration.datacorrection;

import com.deswaef.weakauras.ui.macros.service.MacroService;
import com.deswaef.weakauras.ui.rating.service.ConfigRatingService;
import com.deswaef.weakauras.ui.tellmewhen.service.TellMeWhenService;
import com.deswaef.weakauras.ui.weakauras.service.WeakAuraService;
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
