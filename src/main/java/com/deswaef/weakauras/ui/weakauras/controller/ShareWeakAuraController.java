package com.deswaef.weakauras.ui.weakauras.controller;

import com.deswaef.weakauras.security.CurrentUser;
import com.deswaef.weakauras.ui.mvc.ShareController;
import com.deswaef.weakauras.ui.rating.domain.ConfigRating;
import com.deswaef.weakauras.ui.rating.domain.Rating;
import com.deswaef.weakauras.ui.rating.service.ConfigRatingService;
import com.deswaef.weakauras.ui.tellmewhen.domain.TellMeWhen;
import com.deswaef.weakauras.ui.weakauras.domain.WeakAura;
import com.deswaef.weakauras.ui.weakauras.service.WeakAuraService;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/shared/wa")
public class ShareWeakAuraController implements ShareController {

    private static final String CONFIG_TYPE = "WA";

    @Autowired
    private WeakAuraService weakAuraService;
    @Autowired
    private ConfigRatingService configRatingService;


    @RequestMapping("/{id}")
    public String findWeakauraById(ModelMap modelMap, @PathVariable("id") Long id, @CurrentUser ScrappieUser user) {
        Optional<WeakAura> weakAura = weakAuraService.byId(id);
        if (weakAura.isPresent()) {
            if (isAdmin() || weakAura.get().isApproved()) {
                modelMap.put("screenshots", weakAuraService.findScreenshots(weakAura.get()));
                modelMap.put("configType", CONFIG_TYPE);
                modelMap.put("configId", id);
                modelMap.put("screenshots", weakAuraService.findScreenshots(weakAura.get()));
                modelMap.put("rating", getRating(configRatingService.findByWeakAura(weakAura.get().getId())));
                modelMap.put("config", weakAura.get());
                if (user != null) {
                    modelMap.put("personalRating", configRatingService.getPersonalRatingWA(weakAura.get().getId(), user));
                }
                modelMap.put("isOwn", isOwn(weakAura.get(), user));
                return INDEX_URL;
            } else {
                return PENDING_APPROVAL_URL;
            }
        } else {
            return NOT_FOUND_URL;
        }
    }

    private boolean isOwn(WeakAura weakAura, ScrappieUser user) {
        return (user != null && user.equals(weakAura.getUploader()));
    }

    @RequestMapping(value = "/{id}/positive", method = POST)
    public @ResponseBody
    HttpStatus positiveVote(@CurrentUser ScrappieUser currentUser, @PathVariable("id") Long id) {
        if (currentUser != null) {
            configRatingService.voteWA(id, Rating.POSITIVE, currentUser);
            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @RequestMapping(value = "/{id}/negative", method = POST)
    public
    @ResponseBody
    HttpStatus negativeVote(@CurrentUser ScrappieUser currentUser, @PathVariable("id") Long id) {
        if (currentUser != null) {
            configRatingService.voteWA(id, Rating.NEGATIVE, currentUser);
            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }

}
