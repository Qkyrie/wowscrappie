package com.deswaef.weakauras.usermanagement.controller;

import com.deswaef.weakauras.configuration.social.FacebookAsPrincipalInterceptor;
import com.deswaef.weakauras.usermanagement.util.AuthenticationSecurityInjector;
import com.deswaef.weakauras.usermanagement.util.FacebookUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.UserIdSource;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private ConnectionRepository connectionRepository;

    @Autowired
    private AuthenticationSecurityInjector authenticationSecurityInjector;

    @RequestMapping(method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        if (isAuthenticated() && !hasLogoutParameter(request)) {
            Connection<?> facebook = connectionRepository.findConnections("facebook").get(0);
            authenticationSecurityInjector.injectFacebookId(new FacebookUser()
                    .setFacebookId(facebook.getKey().getProviderUserId())
                    .setUsername(facebook.fetchUserProfile().getFirstName() + facebook.fetchUserProfile().getLastName())
                    .setEmail(facebook.fetchUserProfile().getEmail()));
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null &&
                authentication.isAuthenticated() &&
                !authentication.getPrincipal().equals("anonymousUser")) {
            return "redirect:/";
        } else {
            return "login";
        }
    }

    private boolean hasLogoutParameter(HttpServletRequest request) {
        return request.getParameter("logout") != null;
    }

    private boolean isAuthenticated() {
        List<Connection<?>> facebook = connectionRepository.findConnections("facebook");
        return facebook.size() > 0 && facebook.get(0).fetchUserProfile() != null;
    }
}