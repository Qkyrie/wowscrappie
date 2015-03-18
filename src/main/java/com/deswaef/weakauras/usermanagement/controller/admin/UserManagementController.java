package com.deswaef.weakauras.usermanagement.controller.admin;

import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import com.deswaef.weakauras.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/admin/users")
public class UserManagementController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = GET)
    public String index(ModelMap modelMap) {
        modelMap.put("users", userService.findAll());
        return "admin/users/index";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = GET)
    public String detailsById(ModelMap modelMap, @PathVariable("id") long id) {
        Optional<ScrappieUser> byId = userService.findById(id);
        if (byId.isPresent()) {
            modelMap.put("user", byId.get());
            return "admin/users/index :: detail";
        } else {
            return "admin/users/index :: notfound";
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}/disable", method = GET)
    public
    @ResponseBody
    boolean disableUser(@PathVariable("id") Long id) {
        try {
            userService.setEnabled(id, false);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}/enable", method = GET)
    public
    @ResponseBody
    boolean enableUser(@PathVariable("id") Long id) {
        try {
            userService.setEnabled(id, true);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
