package com.deswaef.wowscrappie.auctionhouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/auctionhouse/overview")
public class AuctionHouseRealmAndItemController {

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "auctionhouse/overview";
    }

}
