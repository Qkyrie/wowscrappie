package com.deswaef.wowscrappie.tools.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod._

@Controller
@RequestMapping(Array("/tools/tierdistribution"))
class TierDistributionController {

  @RequestMapping(method = Array(GET), value = Array("/t18"))
  def tier18: String = "tools/tier_distribution/t18"

}
