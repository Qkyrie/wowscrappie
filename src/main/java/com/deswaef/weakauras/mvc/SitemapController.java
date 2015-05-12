package com.deswaef.weakauras.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sitemap.xml")
public class SitemapController {

    public
    @ResponseBody
    String get() {
        return "worst sitemap ever...";
    }

}
