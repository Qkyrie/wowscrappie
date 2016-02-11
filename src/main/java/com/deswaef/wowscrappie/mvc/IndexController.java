package com.deswaef.wowscrappie.mvc;

import com.deswaef.wowscrappie.security.CurrentUser;
import com.deswaef.wowscrappie.ui.macros.service.MacroService;
import com.deswaef.wowscrappie.ui.tellmewhen.service.TellMeWhenService;
import com.deswaef.wowscrappie.ui.weakauras.service.WeakAuraService;
import com.deswaef.wowscrappie.usermanagement.domain.ScrappieUser;
import com.deswaef.wowscrappie.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/")
public class IndexController {

    @Autowired
    private WeakAuraService weakAuraService;
    @Autowired
    private TellMeWhenService tellMeWhenService;
    @Autowired
    private MacroService macroService;
    @Autowired
    private UserService userService;

    void putDefaultValuesOnModelMap(ModelMap map, HttpServletRequest request) {
        map.put("redirectFrom", getRedirectFrom(request));
        map.put("macroCount", macroService.count());
        map.put("tmwCount", tellMeWhenService.count());
        map.put("waCount", weakAuraService.count());
        map.put("userCount", userService.count());
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap map, @CurrentUser ScrappieUser scrappieUser) {
        putDefaultValuesOnModelMap(map, request);
        return "index";
    }

    private String getRedirectFrom(HttpServletRequest request) {
        if (request.getParameter("thankyou") != null) {
            return "thankyou";
        } else {
            return null;
        }
    }

}
