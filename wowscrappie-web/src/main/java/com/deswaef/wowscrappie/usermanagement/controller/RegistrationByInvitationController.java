package com.deswaef.wowscrappie.usermanagement.controller;

import com.deswaef.wowscrappie.usermanagement.domain.ScrappieUser;
import com.deswaef.wowscrappie.usermanagement.service.UserService;
import com.deswaef.wowscrappie.usermanagement.service.dto.RegistrationByInvitationDto;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequestMapping("/registration/invitation")
public class RegistrationByInvitationController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "registration/invitation-index";
    }

    @RequestMapping("/{registrationid}")
    public String byInvitationCode(ModelMap modelMap, @PathVariable("registrationid") String registrationid) {
        Optional<ScrappieUser> byInvitationCode = userService.findByInvitationCode(registrationid);
        if (byInvitationCode.isPresent()) {
            modelMap.put("invitationcode", registrationid);
            return "registration/invitation";
        } else {
            return "registration/no-invitation-found";
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public RegistrationByInvitationDto acceptInvite(@RequestBody RegistrationByInvitationDto registrationByInvitationDto) {
        Optional<ScrappieUser> byInvitationCode = userService.findByInvitationCode(registrationByInvitationDto.getInvitationcode());
        if (byInvitationCode.isPresent()) {
            ScrappieUser scrappieUser = byInvitationCode.get();
            if (!scrappieUser.getEmail().equalsIgnoreCase(registrationByInvitationDto.getEmail())) {
                return registrationByInvitationDto.setHasErrors(true).setErrorMessage("That was not the email address associated with the invitation code");
            } else if (Strings.isNullOrEmpty(registrationByInvitationDto.getPassword()) || Strings.isNullOrEmpty(registrationByInvitationDto.getPassword_repeat())) {
                return registrationByInvitationDto.setHasErrors(true).setErrorMessage("Please provide a valid password");
            } else {
                try {
                    userService.acceptInvitation(registrationByInvitationDto);
                    return registrationByInvitationDto;
                } catch (Exception ex) {
                    return registrationByInvitationDto.setHasErrors(true).setErrorMessage(ex.getMessage());
                }
            }
        } else {
            return registrationByInvitationDto.setHasErrors(true).setErrorMessage("This registrationcode was not found.");
        }
    }

}
