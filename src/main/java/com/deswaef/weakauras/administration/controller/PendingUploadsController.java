package com.deswaef.weakauras.administration.controller;

import com.deswaef.weakauras.administration.controller.dto.PendingMacroDto;
import com.deswaef.weakauras.administration.controller.dto.PendingTellMeWhenDto;
import com.deswaef.weakauras.administration.controller.dto.PendingWeakAuraDto;
import com.deswaef.weakauras.ui.macros.service.MacroService;
import com.deswaef.weakauras.ui.tellmewhen.service.TellMeWhenService;
import com.deswaef.weakauras.ui.weakauras.service.WeakAuraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Controller
@RequestMapping("/admin/pending-uploads")
public class PendingUploadsController {

    @Autowired
    private MacroService macroService;
    @Autowired
    private TellMeWhenService tellMeWhenService;
    @Autowired
    private WeakAuraService weakAuraService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        modelMap.put("macroCount", getMacroCount());
        modelMap.put("tmwCount", getTmwCount());
        modelMap.put("waCount", getWaCount());
        return "admin/pending_uploads/index";
    }

    @RequestMapping("/count")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody Long countPendingUploads() {
        return getMacroCount() + getTmwCount() + getWaCount();
    }

    @RequestMapping("/uploads/macro")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String pendingMacros(ModelMap model) {
        model.put("uielements", getMacros());
        return "admin/pending_uploads/fragments/results :: macro";
    }

    private List<PendingMacroDto> getMacros() {
        return macroService
                .findAllUnapproved()
                .stream()
                .map(PendingMacroDto::create)
                .collect(toList());
    }

    @RequestMapping("/uploads/tmw")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String pendingTellMeWhens(ModelMap model) {
        model.put("uielements", getTMWs());
        return "admin/pending_uploads/fragments/results :: tmw";
    }

    private List<PendingTellMeWhenDto> getTMWs() {
        return tellMeWhenService
                .findAllNonApproved()
                .stream()
                .map(PendingTellMeWhenDto::create)
                .collect(Collectors.toList());
    }

    @RequestMapping("/uploads/weakaura")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String pendingWeakAuras(ModelMap model) {
        model.put("uielements", getWeakAuras());
        return "admin/pending_uploads/fragments/results :: wa";
    }

    private List<PendingWeakAuraDto> getWeakAuras() {
        return weakAuraService
                .findAllNonApproved()
                .stream()
                .map(PendingWeakAuraDto::create)
                .collect(Collectors.toList());
    }

    private long getWaCount() {
        return weakAuraService.countUnapproved();
    }

    private long getTmwCount() {
        return tellMeWhenService.countUnapproved();
    }

    private long getMacroCount() {
        return macroService.countUnapproved();
    }

}
