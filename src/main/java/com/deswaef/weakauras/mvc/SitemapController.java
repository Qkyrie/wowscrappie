package com.deswaef.weakauras.mvc;

import com.deswaef.weakauras.mvc.sitemap.SitemapGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = "/sitemap.xml")
public class SitemapController {

    @Autowired
    private SitemapGenerator sitemapGenerator;

    @RequestMapping(method = GET)
    @ResponseBody
    public String generate() {
        return sitemapGenerator.generate();
    }

}
