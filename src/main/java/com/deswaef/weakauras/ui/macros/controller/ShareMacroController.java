package com.deswaef.weakauras.ui.macros.controller;

import com.deswaef.weakauras.ui.macros.domain.Macro;
import com.deswaef.weakauras.ui.macros.service.MacroService;
import com.deswaef.weakauras.ui.mvc.ShareController;
import com.deswaef.weakauras.ui.rating.service.ConfigRatingService;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/shared/macro")
public class ShareMacroController implements ShareController {

    public static final String CONFIG_TYPE = "MACRO";

    @Autowired
    private MacroService macroService;
    @Autowired
    private ConfigRatingService configRatingService;

    @RequestMapping("/{id}")
    public String findMacroById(ModelMap modelMap, @PathVariable("id") Long id) {
        Optional<Macro> macro = macroService.byId(id);
        if (macro.isPresent()) {
            if(isAdmin() || macro.get().isApproved()) {
                modelMap.put("screenshots", new ArrayList<>());
                modelMap.put("configType", CONFIG_TYPE);
                modelMap.put("configId", id);
                modelMap.put("config", macro.get());
                modelMap.put("rating", getRating(configRatingService.findByMacro(macro.get().getId())));
                /*
                if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof ScrappieUser) {
                    modelMap.put("personalVote", configRatingService.getPersonalRatingMacro(id, (ScrappieUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
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
