package com.deswaef.weakauras.personalspace.controller;

import com.deswaef.weakauras.personalspace.controller.dto.PersonallyUploadedMacroDto;
import com.deswaef.weakauras.personalspace.controller.dto.PersonallyUploadedTellMeWhenDto;
import com.deswaef.weakauras.personalspace.controller.dto.PersonallyUploadedWeakAuraDto;
import com.deswaef.weakauras.ui.macros.service.MacroService;
import com.deswaef.weakauras.ui.tellmewhen.service.TellMeWhenService;
import com.deswaef.weakauras.ui.weakauras.service.WeakAuraService;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/personal")
@Controller
@PreAuthorize("hasRole('ROLE_USER')")
public class PersonalSpaceController {

    @Autowired
    private MacroService macroService;
    @Autowired
    private TellMeWhenService tellMeWhenService;
    @Autowired
    private WeakAuraService weakAuraService;

    @RequestMapping("/uploads")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String uploads(ModelMap modelMap) {
        Optional<ScrappieUser> currentUser = getCurrentUser();
        if (currentUser.isPresent()) {
            modelMap.put("macroCount", getMacroCount(currentUser.get()));
            modelMap.put("tmwCount", getTmwCount(currentUser.get()));
            modelMap.put("waCount", getWaCount(currentUser.get()));
            return "personal/uploads";
        } else {
            return "index";
        }
    }

    @RequestMapping("/uploads/macro")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String macrosFromUser(ModelMap model) {
        model.put("uielements", getMacros());
        return "personal/fragments/results :: macro";
    }

    @RequestMapping("/uploads/tmw")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String tellMeWhensFromUser(ModelMap model) {
        model.put("uielements", getTMWs());
        return "personal/fragments/results :: tmw";
    }

    @RequestMapping("/uploads/weakaura")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String weakaurasFromUser(ModelMap model) {
        model.put("uielements", getWeakAuras());
        return "personal/fragments/results :: wa";
    }



    private long getTmwCount(ScrappieUser scrappieUser) {
        return tellMeWhenService.countAllFromUser(scrappieUser);
    }

    private long getMacroCount(ScrappieUser scrappieUser) {
        return macroService.countAllFromUser(scrappieUser);
    }

    public long getWaCount(ScrappieUser scrappieUser) {
        return weakAuraService.countAllFromUser(scrappieUser);
    }

    private Optional<ScrappieUser> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof ScrappieUser) {
            return Optional.of((ScrappieUser) authentication.getPrincipal());
        } else {
            return Optional.empty();
        }
    }

    private List<PersonallyUploadedMacroDto> getMacros() {
        Optional<ScrappieUser> currentUser = getCurrentUser();
        if (currentUser.isPresent()) {
            return macroService.findAllFromUser(currentUser.get())
                    .stream()
                    .map(PersonallyUploadedMacroDto::create)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    private List<PersonallyUploadedTellMeWhenDto> getTMWs() {
        Optional<ScrappieUser> currentUser = getCurrentUser();
        if (currentUser.isPresent()) {
            return tellMeWhenService.findAllFromUser(currentUser.get())
                    .stream()
                    .map(PersonallyUploadedTellMeWhenDto::create)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    private List<PersonallyUploadedWeakAuraDto> getWeakAuras() {
        Optional<ScrappieUser> currentUser = getCurrentUser();
        if (currentUser.isPresent()) {
            return weakAuraService.findAllFromUser(currentUser.get())
                    .stream()
                    .map(PersonallyUploadedWeakAuraDto::create)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }
}
