package com.deswaef.wowscrappie.auctionhouse.controller;

import com.deswaef.wowscrappie.auctionhouse.controller.command.CreateAuctionHouseConfigurationCommand;
import com.deswaef.wowscrappie.auctionhouse.service.AuctionHouseSnapshotConfigurationService;
import com.deswaef.wowscrappie.realm.service.RealmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = "/auctionhouse/configuration")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AuctionHouseConfigurationController {

    @Autowired
    private AuctionHouseSnapshotConfigurationService auctionHouseSnapshotConfigurationService;
    @Autowired
    private RealmService realmService;

    @RequestMapping(method = GET)
    public String index(ModelMap modelMap) {
        modelMap.put("configurations", auctionHouseSnapshotConfigurationService.findAll().toList().toBlocking().single());
        return "admin/auctionhouse/configuration";
    }

    @RequestMapping(method = POST, value = "/{configurationId}/requestupdate")
    @ResponseBody
    public ResponseEntity<String> requestUpdate(@PathVariable("configurationId") long configurationId) {
        auctionHouseSnapshotConfigurationService.requestUpdate(configurationId);
        return ResponseEntity.ok("updated");
    }

    @RequestMapping(method = GET, value = "/create")
    public String createIndex(ModelMap modelMap, @ModelAttribute CreateAuctionHouseConfigurationCommand auctionHouseConfigurationCommand) {
        modelMap.put("realms", realmService.findAll()
                .toSortedList((realm1, realm2) -> (realm1.getName() + realm1.getLocality().name()).compareTo(realm2.getName() + realm2.getLocality().name()))
                .toBlocking()
                .single());
        modelMap.put("command", auctionHouseConfigurationCommand);
        return "admin/auctionhouse/newconfiguration";
    }

    @RequestMapping(method = POST, value = "/create")
    public String create(@ModelAttribute("command") CreateAuctionHouseConfigurationCommand command) {
        auctionHouseSnapshotConfigurationService.create(command.getId());
        return "redirect:/auctionhouse/configuration";
    }
}
