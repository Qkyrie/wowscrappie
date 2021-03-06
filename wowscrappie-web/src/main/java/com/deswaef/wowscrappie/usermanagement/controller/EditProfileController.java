package com.deswaef.wowscrappie.usermanagement.controller;

import com.deswaef.wowscrappie.battlenet.api.Battlenet;
import com.deswaef.wowscrappie.battlenet.api.domain.WowProfile;
import com.deswaef.wowscrappie.security.SecurityUtility;
import com.deswaef.wowscrappie.sounds.domain.SoundRepositoryEnum;
import com.deswaef.wowscrappie.usermanagement.domain.ScrappieUser;
import com.deswaef.wowscrappie.usermanagement.domain.UserProfile;
import com.deswaef.wowscrappie.usermanagement.service.UserProfileService;
import com.deswaef.wowscrappie.usermanagement.service.UserService;
import com.deswaef.wowscrappie.usermanagement.service.dto.UserProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

import static com.deswaef.wowscrappie.usermanagement.service.dto.UserProfileDto.create;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/profiles/edit")
public class EditProfileController {

    public static final String USERS_NOT_FOUND = "users/not-found";
    @Autowired
    private UserService userService;
    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    private SecurityUtility securityUtility;
    @Autowired
    private UsersConnectionRepository connectionRepository;

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(method = GET)
    public String editProfile(ModelMap modelMap) {
        Optional<ScrappieUser> currentUser = securityUtility.currentUser();
        if (currentUser.isPresent()) {
            Optional<ScrappieUser> scrappieUser = userService.findById(currentUser.get().getId());
            if (scrappieUser.isPresent()) {
                UserProfile userProfile = userProfileService.findByUser(scrappieUser.get());
                modelMap.put("profile", createProfileDto(scrappieUser, userProfile));
                modelMap.put("soundRepositories", SoundRepositoryEnum.values());

                Connection<Battlenet> primaryConnection = connectionRepository.createConnectionRepository(scrappieUser.get().getUsername())
                        .findPrimaryConnection(Battlenet.class);
                if (primaryConnection != null) {
                    modelMap.put("hasBattleNet", true);
                    modelMap.put("battlenetName", primaryConnection.getDisplayName());
                    try {

                        WowProfile wowProfile = primaryConnection.getApi().userOperations().getWowProfile();
                        modelMap.put("characters", wowProfile);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    modelMap.put("hasBattleNet", false);
                }

                return "users/edit-profile";
            } else {
                return USERS_NOT_FOUND;
            }
        } else {
            return USERS_NOT_FOUND;
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(method = POST)
    @ResponseBody
    public String doEdit(@RequestBody UserProfileDto userProfileDto) {
        Optional<ScrappieUser> currentUser = securityUtility.currentUser();
        if (currentUser.isPresent()) {
            userProfileService.update(userProfileDto, currentUser.get());
            return "/users/" + currentUser.get().getId();
        }
        return "/";
    }

    private UserProfileDto createProfileDto(Optional<ScrappieUser> scrappieUser, UserProfile userProfile) {
        return create(scrappieUser.get(), userProfile);
    }
}
