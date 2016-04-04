package com.deswaef.wowscrappie.notifications.service;

import com.deswaef.wowscrappie.notifications.domain.Notification;
import com.deswaef.wowscrappie.usermanagement.domain.ScrappieUser;
import com.deswaef.wowscrappie.usermanagement.util.AdministratorsCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class NotificationService {

    @Autowired
    private AdministratorsCache administratorsCache;

    @Autowired
    private Optional<SimpMessageSendingOperations> messagingTemplate;

    public void putOnAdminQueue(Notification notification) {
        administratorsCache.getAdmins()
                .stream()
                .forEach(
                        admin -> messagingTemplate.ifPresent(template -> {
                                    template.convertAndSendToUser(admin.getUsername(), "/queue/notifications", notification);
                                }
                        ));
    }

    void broadcastToAdmins(Notification notification) {
        administratorsCache.getAdmins()
                .stream()
                .forEach(
                        admin -> messagingTemplate.ifPresent(template -> {
                                    template.convertAndSendToUser(admin.getUsername(), "/topic/notifications", notification);
                                }
                        ));
    }

    void broadcastTo(ScrappieUser user, Notification notification) {
        messagingTemplate.ifPresent(template -> template.convertAndSendToUser(user.getUsername(), "/topic/notifications", notification));
    }

    public void broadcast(Notification notification) {
        messagingTemplate.ifPresent(template -> template.convertAndSend("/topic/notifications", notification));
    }

}