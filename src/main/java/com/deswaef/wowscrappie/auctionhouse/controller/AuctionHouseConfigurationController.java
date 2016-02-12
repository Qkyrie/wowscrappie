package com.deswaef.wowscrappie.auctionhouse.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = "/auctionhouse/configuration")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AuctionHouseConfigurationController {



    @RequestMapping(method = GET)
    public String index() {
        return "admin/auctionhouse/configuration";
    }


}
