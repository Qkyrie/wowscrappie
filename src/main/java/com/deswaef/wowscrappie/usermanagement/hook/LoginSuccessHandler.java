package com.deswaef.wowscrappie.usermanagement.hook;

import com.deswaef.wowscrappie.notifications.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private NotificationService notificationService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //notificationService.broadcastToAdmins(Notification.create(authentication.getName() + " just logged in!"));
        new SimpleUrlAuthenticationSuccessHandler()
                .onAuthenticationSuccess(request, response, authentication);
    }
}
