package com.deswaef.wowscrappie.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfiguration {

    @Value("${mail.host}")
    private String host;

    @Value("${mail.port}")
    private Integer port;

    @Value("${mail.default.address}")
    private String defaultAddress;

    @Value("${mail.default.authentication.active}")
    private boolean authentication;

    @Value("${mail.default.authentication.username}")
    private String authenticationUsername;

    @Value("${mail.default.authentication.password}")
    private String authenticationPassword;

    @Value("${mail.smtp.localhost}")
    private String heloName;

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);

        Properties properties = new Properties();
        properties.put("mail.smtp.localhost", heloName);
        if (authentication) {
            properties.setProperty("mail.smtp.auth", "true");
            mailSender.setPassword(authenticationPassword);
            mailSender.setUsername(authenticationUsername);
        }
        mailSender.setJavaMailProperties(properties);

        return mailSender;
    }

}
