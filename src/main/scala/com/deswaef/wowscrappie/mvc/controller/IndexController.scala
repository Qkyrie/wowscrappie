package com.deswaef.wowscrappie.mvc.controller

import javax.servlet.http.HttpServletRequest

import com.deswaef.weakauras.security.CurrentUser
import com.deswaef.weakauras.ui.macros.service.MacroService
import com.deswaef.weakauras.ui.tellmewhen.service.TellMeWhenService
import com.deswaef.weakauras.ui.weakauras.service.WeakAuraService
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser
import com.deswaef.weakauras.usermanagement.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.{RequestMethod, RequestMapping}

@Controller
@RequestMapping(value=Array("/"))
class IndexController {
  @Autowired private val weakAuraService: WeakAuraService = null
  @Autowired private val tellMeWhenService: TellMeWhenService = null
  @Autowired private val macroService: MacroService = null
  @Autowired private val userService: UserService = null

  def putDefaultValuesOnModelMap(map: ModelMap, request: HttpServletRequest) : Unit = {
    map put("redirectFrom", getRedirectFrom(request))
    map put("macroCount", long2Long(macroService.count))
    map put("tmwCount", long2Long(tellMeWhenService.count))
    map put("waCount", long2Long(weakAuraService.count))
    map put("userCount", long2Long(userService.count))
  }

  @RequestMapping(method = Array(RequestMethod.GET))
  def index(request: HttpServletRequest, map: ModelMap, @CurrentUser scrappieUser: ScrappieUser): String = {
    putDefaultValuesOnModelMap(map, request)
    "index"
  }

  private def getRedirectFrom(request: HttpServletRequest): String = {
    if (request.getParameter("thankyou") != null) "thankyou" else null
  }
}
