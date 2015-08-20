package com.deswaef.weakauras.ui.macros.controller;

import com.deswaef.weakauras.expansion.service.PatchCalculator;
import com.deswaef.weakauras.security.CurrentUser;
import com.deswaef.weakauras.ui.macros.domain.Macro;
import com.deswaef.weakauras.ui.macros.service.MacroService;
import com.deswaef.weakauras.ui.mvc.ShareController;
import com.deswaef.weakauras.ui.rating.domain.Rating;
import com.deswaef.weakauras.ui.rating.service.ConfigRatingService;
import com.deswaef.weakauras.ui.weakauras.domain.WeakAura;
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

import java.util.ArrayList;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/shared/macro")
public class ShareMacroController implements ShareController {

    public static final String CONFIG_TYPE = "MACRO";

    @Autowired
    private MacroService macroService;
    @Autowired
    private ConfigRatingService configRatingService;
    @Autowired
    private PatchCalculator patchCalculator;

    @RequestMapping("/{id}")
    public String findMacroById(ModelMap modelMap, @PathVariable("id") Long id, @CurrentUser ScrappieUser user) {
        Optional<Macro> macro = macroService.byId(id);
        if (macro.isPresent()) {
            if(isAdmin() || macro.get().isApproved()) {
                modelMap.put("screenshots", new ArrayList<>());
                modelMap.put("configType", CONFIG_TYPE);
                modelMap.put("configId", id);
                modelMap.put("config", macro.get());
                modelMap.put("rating", getRating(configRatingService.findByMacro(macro.get().getId())));
                if (user != null) {
                    modelMap.put("personalRating", configRatingService.getPersonalRatingMacro(macro.get().getId(), user));
                }
                modelMap.put("isOwn", isOwn(macro.get(), user));
                modelMap.put("patch", patchCalculator.calculatePatch(macro.get().getLastUpdateDate()).orElse(null));
                return INDEX_URL;
            } else {
                return PENDING_APPROVAL_URL;
            }
        } else {
            return NOT_FOUND_URL;
        }
    }


    private boolean isOwn(Macro macro, ScrappieUser user) {
        return (user != null && user.equals(macro.getUploader()));
    }

    @RequestMapping(value = "/{id}/positive", method = POST)
    public @ResponseBody
    HttpStatus positiveVote(@CurrentUser ScrappieUser currentUser, @PathVariable("id") Long id) {
        if (currentUser != null) {
            configRatingService.voteMacro(id, Rating.POSITIVE, currentUser);
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
            configRatingService.voteMacro(id, Rating.NEGATIVE, currentUser);
            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }

}
