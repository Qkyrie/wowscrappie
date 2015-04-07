package com.deswaef.weakauras.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.ExpiringSession;
import org.springframework.session.web.socket.config.annotation.AbstractSessionWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableScheduling
@EnableWebSocketMessageBroker
public class WebSocketConfiguration extends AbstractSessionWebSocketMessageBrokerConfigurer<ExpiringSession>{

    protected void configureStompEndpoints(StompEndpointRegistry registry) { // <2>
        registry.addEndpoint("/messages")
                .setAllowedOrigins("http://localhost:8090",
                                    "http://localhost",
                                    "http://wowscrappie.com",
                                    "http://www.wowscrappie.com",
                                    "http://127.0.0.1",
                                    "http://127.0.0.1:8090")
                .withSockJS();
    }

    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/queue/", "/topic/");
        registry.setApplicationDestinationPrefixes("/app");
    }



}
