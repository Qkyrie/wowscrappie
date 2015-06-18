package com.deswaef.weakauras.tools;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/tools/tierdistribution")
public class TierDistributionController {

    @RequestMapping(method = GET, value = "/t18")
    public String tier18() {
        return "tools/tier_distribution/t18";
    }

}
