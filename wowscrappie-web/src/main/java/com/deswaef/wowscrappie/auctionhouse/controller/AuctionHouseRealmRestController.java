package com.deswaef.wowscrappie.auctionhouse.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/auctionhouse/realm/{realmId}")
public class AuctionHouseRealmRestController {

    @RequestMapping(value = "/topsellers")
    public String[] topSellers(@PathVariable("realmId") Long realmId) {
        return new String[]{"Eddy", "Macarena"};
    }

    @RequestMapping(value = "/mostavailable")
    public String[] mostAvailable(@PathVariable("realmId") Long realmId) {
        return new String[]{"Eddy", "Macarena"};
    }


}
