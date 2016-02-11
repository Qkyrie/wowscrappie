package com.deswaef.wowscrappie.message.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/admin/broadcasts")
public class GeneralBroadcastController {

    @RequestMapping(method = GET)
    public String index() {
        return "admin/broadcasts";
    }

}
