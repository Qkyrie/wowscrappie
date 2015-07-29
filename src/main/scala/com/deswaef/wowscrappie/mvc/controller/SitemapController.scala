package com.deswaef.wowscrappie.mvc.controller

import com.deswaef.weakauras.mvc.sitemap.SitemapGenerator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.{RequestMapping, ResponseBody}

@Controller
class SitemapController {

  @Autowired private val sitemapGenerator: SitemapGenerator = null

  @RequestMapping(value = Array("/sitemap.xml"), method = Array(GET))
  @ResponseBody def get: String = sitemapGenerator.generate

}
