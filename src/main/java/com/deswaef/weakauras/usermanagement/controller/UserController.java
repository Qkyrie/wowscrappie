package com.deswaef.weakauras.usermanagement.controller;

import com.deswaef.weakauras.battlenet.api.Battlenet;
import com.deswaef.weakauras.ui.macros.service.MacroService;
import com.deswaef.weakauras.ui.tellmewhen.service.TellMeWhenService;
import com.deswaef.weakauras.ui.weakauras.service.WeakAuraService;
import com.deswaef.weakauras.usermanagement.controller.dto.UserProfileDto;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import com.deswaef.weakauras.usermanagement.domain.UserProfile;
import com.deswaef.weakauras.usermanagement.service.UserProfileService;
import com.deswaef.weakauras.usermanagement.service.UserService;
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

import java.util.Objects;
import java.util.Optional;

import static com.deswaef.weakauras.usermanagement.controller.dto.UserProfileDto.create;

@Controller
@RequestMapping("/users")
public class UserController {

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
}
