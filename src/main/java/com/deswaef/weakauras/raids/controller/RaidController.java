package com.deswaef.weakauras.raids.controller;

import com.deswaef.weakauras.raids.controller.dto.BossConfigurationOverview;
import com.deswaef.weakauras.raids.domain.Raid;
import com.deswaef.weakauras.raids.domain.RaidBoss;
import com.deswaef.weakauras.raids.service.BossService;
import com.deswaef.weakauras.raids.service.RaidService;
import com.deswaef.weakauras.ui.macros.controller.dto.MacroDto;
import com.deswaef.weakauras.ui.macros.service.MacroService;
import com.deswaef.weakauras.ui.tellmewhen.controller.dto.TellMeWhenDto;
import com.deswaef.weakauras.ui.tellmewhen.domain.TellMeWhen;
import com.deswaef.weakauras.ui.tellmewhen.service.TellMeWhenService;
import com.deswaef.weakauras.ui.weakauras.controller.dto.WeakAuraDto;
import com.deswaef.weakauras.ui.weakauras.service.WeakAuraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/raids")
public class RaidController {

    public static final String WEAK_AURA = "weakaura";
    public static final String MACRO = "macro";
    public static final String TMW = "tellmewhen";

    @Autowired
    private RaidService raidService;
    @Autowired
    private BossService bossService;
    @Autowired
    private MacroService macroService;
    @Autowired
    private TellMeWhenService tellMeWhenService;
    @Autowired
    private WeakAuraService weakAuraService;

    @RequestMapping
    public String index() {
        return "raids/index";
    }

    @RequestMapping("/{raidId}")
    public String byRaidId(ModelMap modelMap, @PathVariable("raidId") Long raidId) {
        Optional<Raid> raid = raidService.findById(raidId);
        if(raid.isPresent()) {
            modelMap.put("overview", getBossconfigurations(raid.get()));
            return String.format("raids/%s/%s/index", raid.get().getTier().getName(), raid.get().getSlug());
        } else {
            return  "redirect:/raids";
        }
    }

    @RequestMapping("/{raidId}/bosses/{bossId}/{stringType}")
    public String findUIElementsByBossId(ModelMap modelMap,
                                         @PathVariable("raidId") Long raidId,
                                         @PathVariable("bossId") Long bossId,
                                         @PathVariable("stringType") String stringType) {
        Optional<RaidBoss> byId = bossService.findById(bossId);
        if (byId.isPresent()) {
            if (stringType.equals(TMW)) {
                modelMap.put("uielements", getTellMeWhens(byId.get()));
                return "stringresults/fragments/results :: tmw";
            } else if (stringType.equals(MACRO)) {
                modelMap.put("uielements", getMacros(byId.get()));
                return "stringresults/fragments/results :: macro";
            } else if (stringType.equals(WEAK_AURA)) {
                modelMap.put("uielements", getWeakauras(byId.get()));
                return "stringresults/fragments/results :: wa";
            } else {
                return "stringresults/fragments/results :: error";
            }
        } else {
            return "stringresults/fragments/results :: error";
        }
    }

    private List<WeakAuraDto> getWeakauras(RaidBoss raidBoss) {
        return weakAuraService.findByBoss(raidBoss)
                .stream()
                .map(WeakAuraDto::fromWeakAura)
                .collect(Collectors.toList());
    }

    private List<MacroDto> getMacros(RaidBoss raidBoss) {
        return macroService.findByBoss(raidBoss)
                .stream()
                .map(MacroDto::fromMacro)
                .collect(Collectors.toList());
    }

    private List<TellMeWhenDto> getTellMeWhens(RaidBoss raidBoss) {
        return tellMeWhenService.findByBoss(raidBoss)
                .stream()
                .map(TellMeWhenDto::fromTellMeWhen)
                .collect(Collectors.toList());
    }

    private Map<Long, BossConfigurationOverview> getBossconfigurations(Raid raid) {
        List<RaidBoss> byRaid = bossService.findByRaid(raid);
        Map<Long, BossConfigurationOverview> overview = new HashMap<>();
        for (RaidBoss raidBoss : byRaid) {
            overview.put(raidBoss.getId(), new BossConfigurationOverview()
                    .setMacroCount(getMacroCount(raidBoss))
                    .setWaCount(getWaCount(raidBoss))
                    .setTmwCount(getTmwCount(raidBoss)));
        }
        return overview;
    }

    private long getTmwCount(RaidBoss raidBoss) {
        return tellMeWhenService.countByBoss(raidBoss);
    }

    private long getWaCount(RaidBoss raidBoss) {
        return weakAuraService.countByBoss(raidBoss);
    }

    private long getMacroCount(RaidBoss raidBoss) {
        return macroService.countByBoss(raidBoss);
    }

}
