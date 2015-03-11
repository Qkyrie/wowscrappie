package com.deswaef.weakauras.mvc;

import com.deswaef.weakauras.ui.macros.service.MacroService;
import com.deswaef.weakauras.ui.tellmewhen.service.TellMeWhenService;
import com.deswaef.weakauras.ui.weakauras.service.WeakAuraService;
import com.deswaef.weakauras.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServlet;
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

    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap map) {

        map.put("redirectFrom", getRedirectFrom(request));

        map.put("macroCount", macroService.count());
        map.put("tmwCount", tellMeWhenService.count());
        map.put("waCount", weakAuraService.count());
        map.put("userCount", userService.count());
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
