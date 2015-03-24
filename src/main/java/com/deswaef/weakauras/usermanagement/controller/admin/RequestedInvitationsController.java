package com.deswaef.weakauras.usermanagement.controller.admin;

import com.deswaef.weakauras.usermanagement.service.InvitationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = "/admin/requested-invitations")
public class RequestedInvitationsController {

    @Autowired
    private InvitationRequestService invitationRequestService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = GET)
    public String index(ModelMap modelMap) {
        modelMap.put("invitationRequests", invitationRequestService.findAll());
        return "admin/requested_invitations/index";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = GET)
    public @ResponseBody boolean deleted(@PathVariable("id") Long id) {
        try {
            invitationRequestService.delete(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }


}
