package com.deswaef.weakauras.usermanagement.controller;

import com.deswaef.weakauras.usermanagement.controller.dto.RegistrationDto;
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
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private InvitationRequestService invitationRequestService;

    @RequestMapping(method = GET)
    public String index() {
        return "registration/request";
    }

    @RequestMapping(method = POST)
    public @ResponseBody
    RegistrationDto requestIt(@RequestBody RegistrationDto registrationDto) {
        if (Strings.isNullOrEmpty(registrationDto.getEmail()) || !EmailValidator.getInstance().isValid(registrationDto.getEmail()))  {
            return registrationDto
                    .setHasErrors(true)
                    .setErrorMessage("Please fill in a valid email address");
        } else {
            return invitationRequestService.create(registrationDto);
        }
    }

}
