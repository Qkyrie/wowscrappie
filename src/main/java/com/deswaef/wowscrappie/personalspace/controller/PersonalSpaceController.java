package com.deswaef.wowscrappie.personalspace.controller;

import com.deswaef.wowscrappie.personalspace.controller.dto.PersonallyUploadedMacroDto;
import com.deswaef.wowscrappie.personalspace.controller.dto.PersonallyUploadedTellMeWhenDto;
import com.deswaef.wowscrappie.personalspace.controller.dto.PersonallyUploadedWeakAuraDto;
import com.deswaef.wowscrappie.security.SecurityUtility;
import com.deswaef.wowscrappie.ui.macros.service.MacroService;
import com.deswaef.wowscrappie.ui.rating.domain.ConfigRating;
import com.deswaef.wowscrappie.ui.rating.service.ConfigRatingService;
import com.deswaef.wowscrappie.ui.tellmewhen.service.TellMeWhenService;
import com.deswaef.wowscrappie.ui.weakauras.service.WeakAuraService;
import com.deswaef.wowscrappie.usermanagement.domain.ScrappieUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/personal")
@Controller
@PreAuthorize("hasRole('ROLE_USER')")
public class PersonalSpaceController {

    public static final long DEFAULT_RATING = 0;

    @Autowired
    private MacroService macroService;
    @Autowired
    private TellMeWhenService tellMeWhenService;
    @Autowired
    private WeakAuraService weakAuraService;
    @Autowired
    private SecurityUtility securityUtility;
    @Autowired
    private ConfigRatingService configRatingService;

    @RequestMapping("/uploads")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String uploads(ModelMap modelMap) {
        Optional<ScrappieUser> currentUser = securityUtility.currentUser();
        if (currentUser.isPresent()) {
            modelMap.put("macroCount", getMacroCount(currentUser.get()));
            modelMap.put("tmwCount", getTmwCount(currentUser.get()));
            modelMap.put("waCount", getWaCount(currentUser.get()));
            return "personal/uploads";
        } else {
            return "index";
        }
    }

    @RequestMapping("/uploads/macro")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String macrosFromUser(ModelMap model) {
        model.put("uielements", getMacros());
        return "personal/fragments/results :: macro";
    }

    @RequestMapping("/uploads/tmw")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String tellMeWhensFromUser(ModelMap model) {
        model.put("uielements", getTMWs());
        return "personal/fragments/results :: tmw";
    }

    @RequestMapping("/uploads/weakaura")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String weakaurasFromUser(ModelMap model) {
        model.put("uielements", getWeakAuras());
        return "personal/fragments/results :: wa";
    }



    private long getTmwCount(ScrappieUser scrappieUser) {
        return tellMeWhenService.countAllFromUser(scrappieUser);
    }

    private long getMacroCount(ScrappieUser scrappieUser) {
        return macroService.countAllFromUser(scrappieUser);
    }

    public long getWaCount(ScrappieUser scrappieUser) {
        return weakAuraService.countAllFromUser(scrappieUser);
    }

    private List<PersonallyUploadedMacroDto> getMacros() {
        Optional<ScrappieUser> currentUser = securityUtility.currentUser();
        if (currentUser.isPresent()) {
            return macroService.findAllFromUser(currentUser.get())
                    .stream()
                    .map(PersonallyUploadedMacroDto::create)
                    .map(x -> x.setRating(getRating(configRatingService.findByMacro(x.getId()))))
                    .sorted(getPersonallyUploadedMacroDtoComparator())
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    private Comparator<PersonallyUploadedMacroDto> getPersonallyUploadedMacroDtoComparator() {
        return (o1, o2) -> o1.getRating()<o2.getRating()?-1:
                o1.getRating() >o2.getRating()?1:0;
    }

    private List<PersonallyUploadedTellMeWhenDto> getTMWs() {
        Optional<ScrappieUser> currentUser = securityUtility.currentUser();
        if (currentUser.isPresent()) {
            return tellMeWhenService.findAllFromUser(currentUser.get())
                    .stream()
                    .map(PersonallyUploadedTellMeWhenDto::create)
                    .map(x -> x.setRating(getRating(configRatingService.findByTellMeWhen(x.getId()))))
                    .sorted(getPersonallyUploadedTellMeWhenDtoComparator())
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    private Comparator<PersonallyUploadedTellMeWhenDto> getPersonallyUploadedTellMeWhenDtoComparator() {
        return (o1, o2) -> o1.getRating()<o2.getRating()?-1:
                o1.getRating() >o2.getRating()?1:0;
    }

    private List<PersonallyUploadedWeakAuraDto> getWeakAuras() {
        Optional<ScrappieUser> currentUser = securityUtility.currentUser();
        if (currentUser.isPresent()) {
            return weakAuraService.findAllFromUser(currentUser.get())
                    .stream()
                    .map(PersonallyUploadedWeakAuraDto::create)
                    .map(x -> x.setRating(getRating(configRatingService.findByWeakAura(x.getId()))))
                    .sorted(getPersonallyUploadedWeakAuraDtoComparator())
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    private Comparator<PersonallyUploadedWeakAuraDto> getPersonallyUploadedWeakAuraDtoComparator() {
        return (o1, o2) -> o1.getRating()<o2.getRating()?-1:
                o1.getRating() >o2.getRating()?1:0;
    }

    private long getRating(Optional<? extends ConfigRating> configRating) {
        if (configRating.isPresent()) {
            return configRating.get().calculateEffectiveRating();
        } else {
            return DEFAULT_RATING;
        }
    }
}
