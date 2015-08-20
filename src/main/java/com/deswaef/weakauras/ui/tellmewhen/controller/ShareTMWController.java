package com.deswaef.weakauras.ui.tellmewhen.controller;

import com.deswaef.weakauras.expansion.service.PatchCalculator;
import com.deswaef.weakauras.security.CurrentUser;
import com.deswaef.weakauras.ui.mvc.ShareController;
import com.deswaef.weakauras.ui.rating.domain.Rating;
import com.deswaef.weakauras.ui.rating.service.ConfigRatingService;
import com.deswaef.weakauras.ui.tellmewhen.domain.TellMeWhen;
import com.deswaef.weakauras.ui.tellmewhen.service.TellMeWhenService;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import com.mangofactory.swagger.models.dto.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/shared/tmw")
public class ShareTMWController implements ShareController {
    private static final String CONFIG_TYPE = "TMW";

    @Autowired
    private TellMeWhenService tellMeWhenService;
    @Autowired
    private ConfigRatingService configRatingService;
    @Autowired
    private PatchCalculator patchCalculator;

    @RequestMapping("/{id}")
    public String findTMWById(ModelMap modelMap, @PathVariable("id") Long id, @CurrentUser ScrappieUser user) {
        Optional<TellMeWhen> byId = tellMeWhenService.findById(id);
        if (byId.isPresent()) {
            if(isAdmin() || byId.get().isApproved()) {
                modelMap.put("screenshots", tellMeWhenService.findScreenshots(byId.get()));
                modelMap.put("configType", CONFIG_TYPE);
                modelMap.put("configId", id);
                modelMap.put("rating", getRating(configRatingService.findByTellMeWhen(byId.get().getId())));
                if (user != null) {
                    modelMap.put("personalRating", configRatingService.getPersonalRatingTMW(byId.get().getId(), user));
                }
                modelMap.put("config", byId.get());
                modelMap.put("isOwn", isOwn(byId.get(), user));
                modelMap.put("patch", patchCalculator.calculatePatch(byId.get().getLastUpdateDate()).orElse(null));
            } else {
                return PENDING_APPROVAL_URL;
            }
            return INDEX_URL;
        } else {
            return NOT_FOUND_URL;
        }
    }

    private boolean isOwn(TellMeWhen tellMeWhen, ScrappieUser user) {
        return (user != null && user.equals(tellMeWhen.getUploader()));
    }

    @RequestMapping(value = "/{id}/positive", method = POST)
    public @ResponseBody
    HttpStatus positiveVote(@CurrentUser ScrappieUser currentUser, @PathVariable("id") Long id) {
        if (currentUser != null) {
            configRatingService.voteTMW(id, Rating.POSITIVE, currentUser);
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
            configRatingService.voteTMW(id, Rating.NEGATIVE, currentUser);
            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }

}
