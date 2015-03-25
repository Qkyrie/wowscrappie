package com.deswaef.weakauras.ui.tellmewhen.controller;

import com.deswaef.weakauras.ui.mvc.ShareController;
import com.deswaef.weakauras.ui.rating.service.ConfigRatingService;
import com.deswaef.weakauras.ui.tellmewhen.domain.TellMeWhen;
import com.deswaef.weakauras.ui.tellmewhen.service.TellMeWhenService;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/shared/tmw")
public class ShareTMWController implements ShareController {
    private static final String CONFIG_TYPE = "TMW";

    @Autowired
    private TellMeWhenService tellMeWhenService;
    @Autowired
    private ConfigRatingService configRatingService;

    @RequestMapping("/{id}")
    public String findTMWById(ModelMap modelMap, @PathVariable("id") Long id) {
        Optional<TellMeWhen> byId = tellMeWhenService.findById(id);
        if (byId.isPresent()) {
            if(isAdmin() || byId.get().isApproved()) {
                modelMap.put("screenshots", tellMeWhenService.findScreenshots(byId.get()));
                modelMap.put("configType", CONFIG_TYPE);
                modelMap.put("configId", id);
                modelMap.put("screenshots", tellMeWhenService.findScreenshots(byId.get()));
                modelMap.put("rating", getRating(configRatingService.findByTellMeWhen(byId.get().getId())));
                modelMap.put("config", byId.get());
/*
                if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof ScrappieUser) {
                    modelMap.put("personalVote", configRatingService.getPersonalRatingTMW(id, (ScrappieUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
                }*/
            } else {
                return PENDING_APPROVAL_URL;
            }
            return INDEX_URL;
        } else {
            return NOT_FOUND_URL;
        }
    }

}
