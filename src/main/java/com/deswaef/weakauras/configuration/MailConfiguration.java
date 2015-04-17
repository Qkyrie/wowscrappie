package com.deswaef.weakauras.configuration;


import com.google.common.base.Optional;
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

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);

        if (authentication) {
            Properties properties = new Properties();
            properties.setProperty("mail.smtp.auth", "true");
            mailSender.setJavaMailProperties(properties);
            mailSender.setPassword(authenticationPassword);
            mailSender.setUsername(authenticationUsername);
        }

        return mailSender;
    }

}
