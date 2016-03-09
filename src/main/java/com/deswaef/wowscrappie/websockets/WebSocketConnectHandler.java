/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.deswaef.wowscrappie.websockets;

import java.security.Principal;
import java.util.Arrays;
import java.util.Calendar;

import com.deswaef.wowscrappie.websockets.users.domain.CurrentWebSocketUser;
import com.deswaef.wowscrappie.websockets.users.repository.ActiveWebSocketUserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.socket.messaging.SessionConnectEvent;

public class WebSocketConnectHandler<S> implements ApplicationListener<SessionConnectEvent> {
    private ActiveWebSocketUserRepository repository;
    private SimpMessageSendingOperations messagingTemplate;

    public WebSocketConnectHandler(SimpMessageSendingOperations messagingTemplate, ActiveWebSocketUserRepository repository) {
        super();
        this.messagingTemplate = messagingTemplate;
        this.repository = repository;
    }

    public void onApplicationEvent(SessionConnectEvent event) {
        MessageHeaders headers = event.getMessage().getHeaders();
        Principal user = SimpMessageHeaderAccessor.getUser(headers);
        if(user == null) {
            return;
        }
        String id = SimpMessageHeaderAccessor.getSessionId(headers);
        repository.save(id, new CurrentWebSocketUser(id, user.getName(), Calendar.getInstance()));
        messagingTemplate.convertAndSend("/topic/users/signin", Arrays.asList(user.getName()));
    }
}