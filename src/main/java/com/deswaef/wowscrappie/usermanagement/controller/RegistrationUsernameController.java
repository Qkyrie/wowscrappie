package com.deswaef.wowscrappie.usermanagement.controller;

import com.deswaef.wowscrappie.usermanagement.controller.dto.NameChangeDto;
import com.deswaef.wowscrappie.usermanagement.domain.ScrappieUser;
import com.deswaef.wowscrappie.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLDecoder;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/registration/username")
public class RegistrationUsernameController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/available", method = POST)
    public @ResponseBody boolean usernameAvaible(@RequestBody NameChangeDto requestedUsername) {
        try {
            String decoded = URLDecoder.decode(requestedUsername.getNewName(), "UTF-8");
            Optional<ScrappieUser> byUsername = userService.findByUsername(decoded);
            return !byUsername.isPresent();
        } catch (Exception ex) {
            return false;
        }
    }

    @RequestMapping(value = "/changeit", method = POST)
    public
    @ResponseBody
    boolean changeIt(@RequestBody NameChangeDto requestedUsername) {
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof ScrappieUser) {
            try {
                userService.changeUsername((ScrappieUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), requestedUsername.getNewName());
                return true;
            } catch (Exception ex) {
                return false;
            }
        } else {
            return false;
        }
    }

    @RequestMapping(value = "/leaveit", method = GET)
    public
    @ResponseBody
    boolean leaveit() {
        return changeIt(new NameChangeDto().setNewName(""));
    }

}
