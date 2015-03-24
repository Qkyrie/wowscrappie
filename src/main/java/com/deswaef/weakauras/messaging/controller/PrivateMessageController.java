package com.deswaef.weakauras.messaging.controller;

import com.deswaef.weakauras.messaging.controller.dto.PrivateMessageListDto;
import com.deswaef.weakauras.messaging.service.PrivateMessageService;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/personal/inbox")
public class PrivateMessageController {

    @Autowired
    private PrivateMessageService privateMessageService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        modelMap.put("inbox", privateMessageService.findMessagesToUser(getThisUser())
                .stream()
                .map(PrivateMessageListDto::create)
                .collect(Collectors.toList()));
        return "personal/inbox/index";
    }

    private ScrappieUser getThisUser() {
        return (ScrappieUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
