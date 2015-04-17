package com.deswaef.weakauras.configuration.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.web.ConnectController;

import javax.annotation.PostConstruct;

//@Configuration
public class SocialConfiguration {

    @Value("${com.deswaef.scrappie.fullbaseurl}")
    private String baseUrl;

    @Autowired
    private ConnectController connectController;
    @Autowired
    private FacebookAsPrincipalInterceptor facebookAsPrincipalInterceptor;

    @PostConstruct
    public void init() {
        connectController.addInterceptor(facebookAsPrincipalInterceptor);
        connectController.setApplicationUrl(baseUrl);
    }
}
