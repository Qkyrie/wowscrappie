package com.deswaef.weakauras.mvc;

import com.deswaef.weakauras.mvc.sitemap.SitemapGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SitemapController {

    @Autowired
    private SitemapGenerator sitemapGenerator;

    @RequestMapping(value = "/sitemap.xml", method = RequestMethod.GET)
    public
    @ResponseBody
    String get() {
        return sitemapGenerator.generate();
    }

}
