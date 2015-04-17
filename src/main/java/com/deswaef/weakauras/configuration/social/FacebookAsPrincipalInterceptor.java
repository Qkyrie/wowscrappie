package com.deswaef.weakauras.configuration.social;

import com.deswaef.weakauras.usermanagement.util.AuthenticationSecurityInjector;
import com.deswaef.weakauras.usermanagement.util.AuthenticationSecurityInjectorImpl;
import com.deswaef.weakauras.usermanagement.util.FacebookUser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.web.ConnectInterceptor;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.WebRequest;

//@Component
public class FacebookAsPrincipalInterceptor implements ConnectInterceptor<Facebook> {

    private Log logger = LogFactory.getLog(FacebookAsPrincipalInterceptor.class);

    @Autowired
    private AuthenticationSecurityInjector authenticationSecurityInjector;

    @Override
    public void preConnect(ConnectionFactory<Facebook> connectionFactory, MultiValueMap<String, String> parameters, WebRequest request) {
        //nothing to do here
    }

    @Override
    public void postConnect(Connection<Facebook> connection, WebRequest request) {
        StringBuilder builder = new StringBuilder("Connected to Facebook!\n")
                .append("name: " + connection.getDisplayName() + "\n")
                .append("profile url: " + connection.getProfileUrl() + "\n")
                .append("img: " + connection.getImageUrl() + "\n")
                .append("userid: " + connection.getKey().getProviderUserId());
        logger.info(builder.toString());
        authenticationSecurityInjector.injectFacebookId(new FacebookUser()
                .setFacebookId(connection.getKey().getProviderUserId())
                .setUsername(connection.fetchUserProfile().getFirstName() + connection.fetchUserProfile().getLastName())
                .setEmail(connection.fetchUserProfile().getEmail()));
    }
}
