package com.deswaef.wowscrappie.usermanagement.controller.admin;

import com.deswaef.wowscrappie.usermanagement.domain.ScrappieUser;
import com.deswaef.wowscrappie.usermanagement.service.UserService;
import com.deswaef.wowscrappie.usermanagement.service.dto.CreateInvitationDto;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/admin/registration/invitation")
public class CreateInvitationController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = GET)
    public String index() {
        return "admin/registration/invitation";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    @RequestMapping(method = POST)
    public CreateInvitationDto createInvitation(@RequestBody CreateInvitationDto invitationDto) {
        if (Strings.isNullOrEmpty(invitationDto.getEmail())) {
            return invitationDto.setHasError(true).setErrorMessage("Email cannot be empty.").setInvitationcode("");
        } else if (invitationDto.getEmail().equals("quintendeswaef@gmail.com")) {
            return invitationDto.setHasError(true).setErrorMessage("Email address already in use.").setInvitationcode("");
        } else {
            try {
                ScrappieUser invitation = userService.createInvitation(invitationDto);
                return invitationDto.setHasError(false).setInvitationcode(invitation.getActivationCode());
            } catch (Exception ex) {
                return invitationDto.setHasError(true).setErrorMessage(ex.getMessage());
            }
        }
    }

}
