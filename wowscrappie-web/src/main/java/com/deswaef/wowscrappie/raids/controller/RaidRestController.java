package com.deswaef.wowscrappie.raids.controller;

import com.deswaef.wowscrappie.raids.controller.dto.RaidBossDto;
import com.deswaef.wowscrappie.raids.controller.dto.RaidDto;
import com.deswaef.wowscrappie.raids.domain.Raid;
import com.deswaef.wowscrappie.raids.service.BossService;
import com.deswaef.wowscrappie.raids.service.RaidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/rest/raids")
public class RaidRestController {

    @Autowired
    private RaidService raidService;
    @Autowired
    private BossService bossService;

    @RequestMapping(method = GET)
    public List<RaidDto> getRaids() {
        return raidService
                .findAll()
                .stream()
                .map(RaidDto::create)
                .collect(Collectors.toList());
    }

    @RequestMapping("/{id}")
    public ResponseEntity raidById(@PathVariable("id") long id) {
        Optional<Raid> byId = raidService.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(RaidDto.create(byId.get()));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping("/{id}/bosses")
    public List<RaidBossDto> findBossesByRaid(@PathVariable("id") long id) {
        Optional<Raid> byId = raidService.findById(id);
        if (byId.isPresent()) {
            return bossService.findByRaid(byId.get())
                    .stream()
                    .map(RaidBossDto::create)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

}
