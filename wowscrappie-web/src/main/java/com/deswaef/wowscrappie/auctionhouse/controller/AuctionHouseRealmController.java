package com.deswaef.wowscrappie.auctionhouse.controller;

import com.deswaef.wowscrappie.realm.domain.Realm;
import com.deswaef.wowscrappie.realm.service.RealmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
@RequestMapping(value = "/auctionhouse/{realm}")
public class AuctionHouseRealmController {

    @Autowired
    private RealmService realmService;

    @RequestMapping(method = RequestMethod.GET)
    public String realmOverview(@PathVariable("realm") Long realm,
                                ModelMap modelMap) {
        Optional<Realm> theRealm = realmService.findOne(realm);
        if (!theRealm.isPresent()) {
            return "redirect:/realm/choose";
        } else {
            modelMap.put("realmId", theRealm.get().getId());
            return "auctionhouse/realmoverview";
        }
    }

}
