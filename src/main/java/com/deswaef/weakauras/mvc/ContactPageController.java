package com.deswaef.weakauras.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/contact")
public class ContactPageController {

    @RequestMapping(method = GET)
    public String index() {
        return "contact";
    }

}
