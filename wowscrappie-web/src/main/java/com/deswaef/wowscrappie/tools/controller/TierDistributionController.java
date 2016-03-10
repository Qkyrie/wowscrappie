package com.deswaef.wowscrappie.tools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/tools/tierdistribution")
public class TierDistributionController {

    @RequestMapping(method = RequestMethod.GET, value = "/t18")
    public String tier18() {
        return "tools/tier_distribution/t18";
    }

}
