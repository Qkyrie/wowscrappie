package com.deswaef.weakauras.ui.weakauras.controller;

import com.deswaef.weakauras.ui.mvc.ShareController;
import com.deswaef.weakauras.ui.rating.domain.ConfigRating;
import com.deswaef.weakauras.ui.rating.service.ConfigRatingService;
import com.deswaef.weakauras.ui.weakauras.domain.WeakAura;
import com.deswaef.weakauras.ui.weakauras.service.WeakAuraService;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/shared/wa")
public class ShareWeakAuraController implements ShareController {

    private static final String CONFIG_TYPE = "WA";

    @Autowired
    private WeakAuraService weakAuraService;
    @Autowired
    private ConfigRatingService configRatingService;


    @RequestMapping("/{id}")
    public String findWeakauraById(ModelMap modelMap, @PathVariable("id") Long id) {
        Optional<WeakAura> weakAura = weakAuraService.byId(id);
        if (weakAura.isPresent()) {
            if (isAdmin() || weakAura.get().isApproved()) {
                modelMap.put("screenshots", weakAuraService.findScreenshots(weakAura.get()));
                modelMap.put("configType", CONFIG_TYPE);
                modelMap.put("configId", id);
                modelMap.put("screenshots", weakAuraService.findScreenshots(weakAura.get()));
                modelMap.put("rating", getRating(configRatingService.findByMacro(weakAura.get().getId())));
                modelMap.put("config", weakAura.get());
                /*
                if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof ScrappieUser) {
                    modelMap.put("personalVote", configRatingService.getPersonalRatingWA(id, (ScrappieUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
                } */
                return INDEX_URL;
            } else {
                return PENDING_APPROVAL_URL;
            }
        } else {
            return NOT_FOUND_URL;
        }
    }

}
