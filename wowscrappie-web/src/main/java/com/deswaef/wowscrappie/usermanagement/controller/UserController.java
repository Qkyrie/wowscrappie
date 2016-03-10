package com.deswaef.wowscrappie.usermanagement.controller;

import com.deswaef.wowscrappie.battlenet.api.Battlenet;
import com.deswaef.wowscrappie.personalspace.controller.dto.PersonallyUploadedMacroDto;
import com.deswaef.wowscrappie.personalspace.controller.dto.PersonallyUploadedTellMeWhenDto;
import com.deswaef.wowscrappie.personalspace.controller.dto.PersonallyUploadedWeakAuraDto;
import com.deswaef.wowscrappie.ui.macros.service.MacroService;
import com.deswaef.wowscrappie.ui.rating.domain.ConfigRating;
import com.deswaef.wowscrappie.ui.rating.service.ConfigRatingService;
import com.deswaef.wowscrappie.ui.tellmewhen.service.TellMeWhenService;
import com.deswaef.wowscrappie.ui.weakauras.service.WeakAuraService;
import com.deswaef.wowscrappie.usermanagement.service.dto.UserProfileDto;
import com.deswaef.wowscrappie.usermanagement.domain.ScrappieUser;
import com.deswaef.wowscrappie.usermanagement.domain.UserProfile;
import com.deswaef.wowscrappie.usermanagement.service.UserProfileService;
import com.deswaef.wowscrappie.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.deswaef.wowscrappie.usermanagement.service.dto.UserProfileDto.create;

@Controller
@RequestMapping("/users")
public class UserController {

    public static final long DEFAULT_RATING = 0;

    @Autowired
    private UserService userService;
    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    private MacroService macroService;
    @Autowired
    private TellMeWhenService tellMeWhenService;
    @Autowired
    private WeakAuraService weakAuraService;
    @Autowired
    private UsersConnectionRepository connectionRepository;
    @Autowired
    private ConfigRatingService configRatingService;

    @RequestMapping("/count")
    public @ResponseBody Long count() {
        return userService.count();
    }

    @RequestMapping(value = "/{id}")
    public String getProfile(ModelMap modelmap, @PathVariable("id") Long id) {
        Optional<ScrappieUser> scrappieUser = userService.findById(id);
        if (scrappieUser.isPresent()) {
            UserProfile userProfile = userProfileService.findByUser(scrappieUser.get());
            modelmap.put("profile", createProfileDto(scrappieUser, userProfile));
            modelmap.put("score", getFullScore(scrappieUser.get()));
            modelmap.put("canEdit", canEdit(scrappieUser.get()));

            Connection<Battlenet> primaryConnection = connectionRepository.createConnectionRepository(scrappieUser.get().getUsername())
                    .findPrimaryConnection(Battlenet.class);
            if(primaryConnection != null) {
                modelmap.put("hasBattleNet", true);
                modelmap.put("battlenetName", primaryConnection.getDisplayName());
            } else {
                modelmap.put("hasBattleNet", false);
            }
            return "users/profile";
        } else {
            return "users/not-found";
        }
    }

    private String getFullScore(ScrappieUser scrappieUser) {
        long score = userService.calculateUserScore(scrappieUser);
        return String.format("%s %s", score, score == 1 || score == -1 ? "point" : "points");
    }

    @RequestMapping("/{id}/uploads/macro")
    public String macrosFromUser(ModelMap model, @PathVariable("id") long userId) {
        model.put("uielements", getMacrosFromUser(userId));
        return "users/fragments/results :: macro";
    }

    @RequestMapping("/{id}/uploads/tmw")
    public String tellMeWhensFromUser(ModelMap model, @PathVariable("id") long userId) {
        model.put("uielements", getTMWsFromUser(userId));
        return "users/fragments/results :: tmw";
    }

    @RequestMapping("/{id}/uploads/weakaura")
    public String weakaurasFromUser(ModelMap model, @PathVariable("id") long userId) {
        model.put("uielements", getWeakAurasFromUser(userId));
        return "users/fragments/results :: wa";
    }

    private List<PersonallyUploadedMacroDto> getMacrosFromUser(long userId) {
        Optional<ScrappieUser> currentUser = userService.findById(userId);
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

    private boolean canEdit(ScrappieUser scrappieUser) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null && authentication.getPrincipal() instanceof ScrappieUser) {
            return Objects.equals(((ScrappieUser) authentication.getPrincipal()).getId(), scrappieUser.getId());
        } else {
            return false;
        }
    }

    private UserProfileDto createProfileDto(Optional<ScrappieUser> scrappieUser, UserProfile userProfile) {
        return create(scrappieUser.get(), userProfile)
                .setMacros(getMacros(scrappieUser))
                .setWas(getWas(scrappieUser))
                .setTmws(getTmw(scrappieUser));
    }

    private long getTmw(Optional<ScrappieUser> scrappieUser) {
        return tellMeWhenService.countAllFromUser(scrappieUser.get());
    }

    private long getWas(Optional<ScrappieUser> scrappieUser) {
        return weakAuraService.countAllFromUser(scrappieUser.get());
    }

    private long getMacros(Optional<ScrappieUser> scrappieUser) {
        return macroService.countAllFromUser(scrappieUser.get());
    }

    private Comparator<PersonallyUploadedMacroDto> getPersonallyUploadedMacroDtoComparator() {
        return (o1, o2) -> o1.getRating()<o2.getRating()?-1:
                o1.getRating() >o2.getRating()?1:0;
    }

    private List<PersonallyUploadedTellMeWhenDto> getTMWsFromUser(long userId) {
        Optional<ScrappieUser> theUser = userService.findById(userId);
        if (theUser.isPresent()) {
            return tellMeWhenService.findAllFromUser(theUser.get())
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

    private List<PersonallyUploadedWeakAuraDto> getWeakAurasFromUser(long userId) {
        Optional<ScrappieUser> theUser = userService.findById(userId);
        if (theUser.isPresent()) {
            return weakAuraService.findAllFromUser(theUser.get())
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
