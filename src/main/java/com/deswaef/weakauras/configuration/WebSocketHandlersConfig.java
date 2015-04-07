package com.deswaef.weakauras.configuration;

import com.deswaef.weakauras.websockets.WebSocketConnectHandler;
import com.deswaef.weakauras.websockets.WebSocketDisconnectHandler;
import com.deswaef.weakauras.websockets.users.repository.ActiveWebSocketUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.session.ExpiringSession;

/**
 * These handlers are separated from WebSocketConfig because they are specific to this application and do not demonstrate a typical Spring Session setup.
 *
 * @author Rob Winch
 */
@Configuration
public class WebSocketHandlersConfig<S extends ExpiringSession> {

    @Bean
    public WebSocketConnectHandler<S> webSocketConnectHandler(SimpMessageSendingOperations messagingTemplate, ActiveWebSocketUserRepository repository) {
        return new WebSocketConnectHandler<S>(messagingTemplate, repository);
    }

    @Bean
    public WebSocketDisconnectHandler<S> webSocketDisconnectHandler(SimpMessageSendingOperations messagingTemplate, ActiveWebSocketUserRepository repository) {
        return new WebSocketDisconnectHandler<S>(messagingTemplate, repository);
    }
}
