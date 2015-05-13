package com.deswaef.weakauras.battlenet.controller;

import com.deswaef.weakauras.battlenet.api.Battlenet;
import com.deswaef.weakauras.security.CurrentUser;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/connect/battlenetConnected")
public class BattlenetController {

    @Autowired
    private UsersConnectionRepository connectionRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method= RequestMethod.GET)
    public String home(@CurrentUser ScrappieUser currentUser, ModelMap model) {
        Connection<Battlenet> connection = connectionRepository.createConnectionRepository(currentUser.getUsername())
                .getPrimaryConnection(Battlenet.class);
        if (connection == null) {
            return "redirect:/profiles/edit";
        }
        model.addAttribute("profile", connection.getApi().userOperations().getUserProfile());
        return "redirect:/profiles/edit";
    }
}
