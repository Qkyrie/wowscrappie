package com.deswaef.weakauras.configuration.social;

import com.deswaef.weakauras.usermanagement.util.FacebookUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

//@Component
public class FacebookLogoutHandler implements LogoutHandler {

    @Autowired
    private ConnectionRepository connectionRepository;
    @Autowired
    private ConnectController connectController;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (isAuthenticated()) {
            Connection<?> facebook = connectionRepository.findConnections("facebook").get(0);
            connectController.removeConnections("facebook", new ServletWebRequest(request));
        }
    }

    private boolean isAuthenticated() {
        List<Connection<?>> facebook = connectionRepository.findConnections("facebook");
        return facebook.size() > 0 && facebook.get(0).fetchUserProfile() != null;
    }
}
