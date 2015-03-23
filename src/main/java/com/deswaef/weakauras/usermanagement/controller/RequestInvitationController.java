package com.deswaef.weakauras.usermanagement.controller;

import com.deswaef.weakauras.usermanagement.controller.dto.RequestInvitationDto;
import com.deswaef.weakauras.usermanagement.service.InvitationRequestService;
import com.google.common.base.Strings;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/registration/request-invitation")
public class RequestInvitationController {

    @Autowired
    private InvitationRequestService invitationRequestService;


    @RequestMapping(method = GET)
    public String index() {
        return "registration/request-invitation";
    }

    @RequestMapping(method = POST)
    public @ResponseBody RequestInvitationDto requestIt(@RequestBody RequestInvitationDto requestInvitationDto) {
        if (Strings.isNullOrEmpty(requestInvitationDto.getEmail()) || !EmailValidator.getInstance().isValid(requestInvitationDto.getEmail()))  {
            return requestInvitationDto
                    .setHasErrors(true)
                    .setErrorMessage("Please fill in a valid email address");
        } else if (Strings.isNullOrEmpty(requestInvitationDto.getReason())) {
            return requestInvitationDto
                    .setHasErrors(true)
                    .setErrorMessage("Please fill in a reason why you want to join this testing phase");
        } else {
            return invitationRequestService.create(requestInvitationDto);
        }
    }

}
