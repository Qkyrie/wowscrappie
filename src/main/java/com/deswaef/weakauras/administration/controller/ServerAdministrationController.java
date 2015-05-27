package com.deswaef.weakauras.administration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/admin/server")
public class ServerAdministrationController {

    @RequestMapping(method = GET)
    public String server() {
        return "admin/manage/server";
    }
}
