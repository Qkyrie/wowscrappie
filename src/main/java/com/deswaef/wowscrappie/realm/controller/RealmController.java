package com.deswaef.wowscrappie.realm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = "/realm")
public class RealmController {

    @RequestMapping(value = "/choose", method = GET)
    public String pickRealmIndex(ModelMap model) {
        return "realms/choose";
    }


}
