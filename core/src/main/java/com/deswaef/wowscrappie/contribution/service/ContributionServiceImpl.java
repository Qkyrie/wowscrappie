package com.deswaef.wowscrappie.contribution.service;

import com.deswaef.wowscrappie.classes.domain.WowClass;
import com.deswaef.wowscrappie.classes.service.ClassService;
import com.deswaef.wowscrappie.classes.service.SpecService;
import com.deswaef.wowscrappie.contribution.service.dto.ContributionDto;
import com.deswaef.wowscrappie.raids.domain.RaidBoss;
import com.deswaef.wowscrappie.raids.service.BossService;
import com.deswaef.wowscrappie.security.SecurityUtility;
import com.deswaef.wowscrappie.ui.macros.domain.BossFightMacro;
import com.deswaef.wowscrappie.ui.macros.domain.Macro;
import com.deswaef.wowscrappie.ui.macros.domain.SpecMacro;
import com.deswaef.wowscrappie.ui.macros.domain.WowClassMacro;
import com.deswaef.wowscrappie.ui.macros.service.MacroService;
import com.deswaef.wowscrappie.ui.rating.service.ConfigRatingService;
import com.deswaef.wowscrappie.ui.tellmewhen.domain.BossFightTellMeWhen;
import com.deswaef.wowscrappie.ui.tellmewhen.domain.SpecTellMeWhen;
import com.deswaef.wowscrappie.ui.tellmewhen.domain.TellMeWhen;
import com.deswaef.wowscrappie.ui.tellmewhen.domain.WowClassTellMeWhen;
import com.deswaef.wowscrappie.ui.tellmewhen.service.TellMeWhenService;
import com.deswaef.wowscrappie.ui.weakauras.domain.BossFightWeakAura;
import com.deswaef.wowscrappie.ui.weakauras.domain.SpecWeakAura;
import com.deswaef.wowscrappie.ui.weakauras.domain.WeakAura;
import com.deswaef.wowscrappie.ui.weakauras.domain.WowClassWeakAura;
import com.deswaef.wowscrappie.ui.weakauras.service.WeakAuraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
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
    @Autowired
    private SecurityUtility securityUtility;

    @Override
    @Transactional
    public void contribute(ContributionDto contribution) {
        String category = contribution.category();
        if (category.equals(ContributionDto.CATEGORY_TMW)) {
            contributeTMW(contribution);
        } else if (category.equals(ContributionDto.CATEGORY_WA)) {
            contributeWA(contribution);
        } else if (category.equals(ContributionDto.CATEGORY_MACRO)) {
            contributeMacro(contribution);
        }
    }

    private void contributeTMW(ContributionDto contribution) {
        TellMeWhen newTellMeWhen = null;
        if (contribution.chooseOption().equals(ContributionDto.OPTION_CLASSSPEC)) {
            WowClass wowClass = classService.bySlug(contribution.wowClass()).get();
            if (contribution.spec().equals("class_specific")) {
                newTellMeWhen = new WowClassTellMeWhen().setWowClass(wowClass);
            } else {
                newTellMeWhen = new SpecTellMeWhen().setSpec(specService.bySlug(wowClass, contribution.spec()).get());
            }
        } else if(contribution.chooseOption().equals(ContributionDto.OPTION_BOSSFIGHT)) {
            Optional<RaidBoss> byId = bossService.findById(contribution.bossId());
            if (byId.isPresent()) {
                newTellMeWhen = new BossFightTellMeWhen()
                                    .setBoss(byId.get());
            }
        }
        if (newTellMeWhen != null) {
            TellMeWhen tmw = tellMeWhenService.createTMW(
                    newTellMeWhen
                            .setLastUpdateDate(now())
                            .setActualValue(contribution.actualValue())
                            .setName(contribution.caption())
                            .setComment(contribution.comments())
                            .setUploader(securityUtility.currentUser().get())
            );
            configRatingService.createTMWRating(tmw);
            if (contribution.screenshots() != null && contribution.screenshots().length > 0) {
                Arrays.asList(contribution.screenshots())
                        .stream()
                        .forEach(ss -> tellMeWhenService.saveScreenshot(ss, tmw));
            }
        }
    }

    private Date now() {
        return new Date();
    }

    private void contributeWA(ContributionDto contribution) {
        WeakAura newWeakaura = null;
        if (contribution.chooseOption().equals(ContributionDto.OPTION_CLASSSPEC)) {
            WowClass wowClass = classService.bySlug(contribution.wowClass()).get();
            if (contribution.spec().equals("class_specific")) {
                newWeakaura = new WowClassWeakAura().setWowClass(classService.bySlug(contribution.wowClass()).get());
            } else {
                newWeakaura = new SpecWeakAura().setSpec(specService.bySlug(wowClass, contribution.spec()).get());
            }

        } else if (contribution.chooseOption().equals(ContributionDto.OPTION_BOSSFIGHT)) {
            Optional<RaidBoss> byId = bossService.findById(contribution.bossId());
            if (byId.isPresent()) {
                newWeakaura = new BossFightWeakAura().setBoss(byId.get());
            }
        }
        if (newWeakaura != null) {
            WeakAura wa = weakAuraService.createWeakAura(
                    newWeakaura
                            .setLastUpdateDate(now())
                            .setActualValue(contribution.actualValue())
                            .setName(contribution.caption())
                            .setComment(contribution.comments())
                            .setUploader(securityUtility.currentUser().get())
            );
            configRatingService.createWARating(wa);
            if (contribution.screenshots() != null && contribution.screenshots().length > 0) {
                Arrays.asList(contribution.screenshots())
                        .stream()
                        .forEach(ss -> weakAuraService.saveScreenshot(ss, wa));
            }
        }
    }

    private void contributeMacro(ContributionDto contribution) {
        Macro newMacro = null;
        if (contribution.chooseOption().equals(ContributionDto.OPTION_CLASSSPEC)) {
            WowClass wowClass = classService.bySlug(contribution.wowClass()).get();
            if (contribution.spec().equals("class_specific")) {
                newMacro = new WowClassMacro().setWowClass(classService.bySlug(contribution.wowClass()).get());
            } else {
                newMacro = new SpecMacro().setSpec(specService.bySlug(wowClass, contribution.spec()).get());
            }

        } else if (contribution.chooseOption().equals(ContributionDto.OPTION_BOSSFIGHT)) {
            Optional<RaidBoss> byId = bossService.findById(contribution.bossId());
            if (byId.isPresent()) {
                newMacro = new BossFightMacro().setBoss(byId.get());
            }
        }
        if (newMacro != null) {
            Macro macro = macroService.newMacro(
                    newMacro
                            .setLastUpdateDate(now())
                            .setActualValue(contribution.actualValue())
                            .setName(contribution.caption())
                            .setDescription(contribution.comments())
                            .setUploader(securityUtility.currentUser().get())
            );
            configRatingService.createMacroRating(macro);
        }
    }
}
