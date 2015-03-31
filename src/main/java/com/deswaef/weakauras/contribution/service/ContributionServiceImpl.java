package com.deswaef.weakauras.contribution.service;

import com.deswaef.weakauras.classes.domain.WowClass;
import com.deswaef.weakauras.classes.service.ClassService;
import com.deswaef.weakauras.classes.service.SpecService;
import com.deswaef.weakauras.contribution.controller.dto.ContributionCommand;
import com.deswaef.weakauras.raids.domain.RaidBoss;
import com.deswaef.weakauras.raids.service.BossService;
import com.deswaef.weakauras.ui.macros.domain.BossFightMacro;
import com.deswaef.weakauras.ui.macros.domain.Macro;
import com.deswaef.weakauras.ui.macros.domain.SpecMacro;
import com.deswaef.weakauras.ui.macros.domain.WowClassMacro;
import com.deswaef.weakauras.ui.macros.service.MacroService;
import com.deswaef.weakauras.ui.rating.service.ConfigRatingService;
import com.deswaef.weakauras.ui.tellmewhen.domain.BossFightTellMeWhen;
import com.deswaef.weakauras.ui.tellmewhen.domain.SpecTellMeWhen;
import com.deswaef.weakauras.ui.tellmewhen.domain.TellMeWhen;
import com.deswaef.weakauras.ui.tellmewhen.domain.WowClassTellMeWhen;
import com.deswaef.weakauras.ui.tellmewhen.service.TellMeWhenService;
import com.deswaef.weakauras.ui.weakauras.domain.BossFightWeakAura;
import com.deswaef.weakauras.ui.weakauras.domain.SpecWeakAura;
import com.deswaef.weakauras.ui.weakauras.domain.WeakAura;
import com.deswaef.weakauras.ui.weakauras.domain.WowClassWeakAura;
import com.deswaef.weakauras.ui.weakauras.service.WeakAuraService;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import com.deswaef.weakauras.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;


@Service
public class ContributionServiceImpl implements ContributionService {

    @Autowired
    private TellMeWhenService tellMeWhenService;
    @Autowired
    private ClassService classService;
    @Autowired
    private SpecService specService;
    @Autowired
    private WeakAuraService weakAuraService;
    @Autowired
    private MacroService macroService;
    @Autowired
    private ConfigRatingService configRatingService;
    @Autowired
    private BossService bossService;

    @Override
    @Transactional
    public void contribute(ContributionCommand contributionCommand) {
        String category = contributionCommand.getCategory();
        if (category.equals(ContributionCommand.CATEGORY_TMW)) {
            contributeTMW(contributionCommand);
        } else if (category.equals(ContributionCommand.CATEGORY_WA)) {
            contributeWA(contributionCommand);
        } else if (category.equals(ContributionCommand.CATEGORY_MACRO)) {
            contributeMacro(contributionCommand);
        }
    }

    private void contributeTMW(ContributionCommand command) {
        TellMeWhen newTellMeWhen = null;
        if (command.getChooseOption().equals(ContributionCommand.OPTION_CLASSSPEC)) {
            WowClass wowClass = classService.bySlug(command.getWowClass()).get();
            if (command.getSpec().equals("class_specific")) {
                newTellMeWhen = new WowClassTellMeWhen().setWowClass(wowClass);
            } else {
                newTellMeWhen = new SpecTellMeWhen().setSpec(specService.bySlug(wowClass, command.getSpec()).get());
            }
        } else if(command.getChooseOption().equals(ContributionCommand.OPTION_BOSSFIGHT)) {
            Optional<RaidBoss> byId = bossService.findById(command.getBossId());
            if (byId.isPresent()) {
                newTellMeWhen = new BossFightTellMeWhen()
                                    .setBoss(byId.get());
            }
        }
        if (newTellMeWhen != null) {
            TellMeWhen tmw = tellMeWhenService.createTMW(
                    newTellMeWhen
                            .setActualValue(command.getActualValue())
                            .setName(command.getCaption())
                            .setComment(command.getComments())
                            .setUploader(getCurrentUser())
            );
            configRatingService.createTMWRating(tmw);
            if (command.getScreenshots() != null && command.getScreenshots().length > 0) {
                Arrays.asList(command.getScreenshots())
                        .stream()
                        .forEach(ss -> tellMeWhenService.saveScreenshot(ss, tmw));
            }
        }
    }

    private void contributeWA(ContributionCommand command) {
        WeakAura newWeakaura = null;
        if (command.getChooseOption().equals(ContributionCommand.OPTION_CLASSSPEC)) {
            WowClass wowClass = classService.bySlug(command.getWowClass()).get();
            if (command.getSpec().equals("class_specific")) {
                newWeakaura = new WowClassWeakAura().setWowClass(classService.bySlug(command.getWowClass()).get());
            } else {
                newWeakaura = new SpecWeakAura().setSpec(specService.bySlug(wowClass, command.getSpec()).get());
            }

        } else if (command.getChooseOption().equals(ContributionCommand.OPTION_BOSSFIGHT)) {
            Optional<RaidBoss> byId = bossService.findById(command.getBossId());
            if (byId.isPresent()) {
                newWeakaura = new BossFightWeakAura().setBoss(byId.get());
            }
        }
        if (newWeakaura != null) {
            WeakAura wa = weakAuraService.createWeakAura(
                    newWeakaura
                            .setActualValue(command.getActualValue())
                            .setName(command.getCaption())
                            .setComment(command.getComments())
                            .setUploader(getCurrentUser())
            );
            configRatingService.createWARating(wa);
            if (command.getScreenshots() != null && command.getScreenshots().length > 0) {
                Arrays.asList(command.getScreenshots())
                        .stream()
                        .forEach(ss -> weakAuraService.saveScreenshot(ss, wa));
            }
        }
    }

    private void contributeMacro(ContributionCommand command) {
        Macro newMacro = null;
        if (command.getChooseOption().equals(ContributionCommand.OPTION_CLASSSPEC)) {
            WowClass wowClass = classService.bySlug(command.getWowClass()).get();
            if (command.getSpec().equals("class_specific")) {
                newMacro = new WowClassMacro().setWowClass(classService.bySlug(command.getWowClass()).get());
            } else {
                newMacro = new SpecMacro().setSpec(specService.bySlug(wowClass, command.getSpec()).get());
            }

        } else if (command.getChooseOption().equals(ContributionCommand.OPTION_BOSSFIGHT)) {
            Optional<RaidBoss> byId = bossService.findById(command.getBossId());
            if (byId.isPresent()) {
                newMacro = new BossFightMacro().setBoss(byId.get());
            }
        }
        if (newMacro != null) {
            Macro macro = macroService.newMacro(
                    newMacro
                            .setActualValue(command.getActualValue())
                            .setName(command.getCaption())
                            .setDescription(command.getComments())
                            .setUploader(getCurrentUser())
            );
            configRatingService.createMacroRating(macro);
        }
    }

    private ScrappieUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.isAuthenticated() == false || authentication.getName().equals("anonymousUser")) {
            throw new IllegalArgumentException("User is not logged in");
        } else {
            return (ScrappieUser)authentication.getPrincipal();
        }
    }
}
