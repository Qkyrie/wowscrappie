package com.deswaef.wowscrappie.auctionhouse.controller;

import com.deswaef.wowscrappie.realm.CurrentRealm;
import com.deswaef.wowscrappie.realm.domain.Realm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
@RequestMapping(value = "/auctionhouse/overview")
public class AuctionHouseRealmAndItemController {

    @RequestMapping(method = RequestMethod.GET)
    public String index(@CurrentRealm Optional<Realm> currentRealm) {
        if (currentRealm.isPresent()) {
            return "auctionhouse/overview";
        } else {
            return "redirect:/realm/choose";
        }
    }

}
