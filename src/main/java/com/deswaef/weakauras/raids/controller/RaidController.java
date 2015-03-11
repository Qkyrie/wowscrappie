package com.deswaef.weakauras.raids.controller;

import com.deswaef.weakauras.raids.domain.Raid;
import com.deswaef.weakauras.raids.service.RaidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping(value = "/raids")
public class RaidController {

    @Autowired
    private RaidService raidService;

    @RequestMapping
    public String index() {
        return "raids/index";
    }

    @RequestMapping("/{raidId}")
    public String byRaidId(@PathVariable("raidId") Long raidId) {
        Optional<Raid> raid = raidService.findById(raidId);
        if(raid.isPresent()) {
            return String.format("raids/%s/%s/index", raid.get().getTier().getName(), raid.get().getSlug());
        } else {
            return  "redirect:/raids";
        }
    }

}
