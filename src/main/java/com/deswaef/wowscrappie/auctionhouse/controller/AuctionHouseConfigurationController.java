package com.deswaef.wowscrappie.auctionhouse.controller;

import com.deswaef.wowscrappie.auctionhouse.service.AuctionHouseSnapshotConfigurationService;
import com.deswaef.wowscrappie.realm.service.RealmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

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

    @RequestMapping(method = RequestMethod.POST, value="/{configurationId}/requestupdate")
    @ResponseBody
    public ResponseEntity<String> requestUpdate(@PathVariable("configurationId") long configurationId) {
        auctionHouseSnapshotConfigurationService.requestUpdate(configurationId);
        return ResponseEntity.ok("updated");
    }

    @RequestMapping(method = GET, value = "/create")
    public String createIndex(ModelMap modelMap) {
        modelMap.put("realms", realmService.findAll());
        return "admin/auctionhouse/newconfiguration";
    }

}
