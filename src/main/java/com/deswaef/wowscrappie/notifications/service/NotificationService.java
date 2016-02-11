package com.deswaef.wowscrappie.notifications.service;

import com.deswaef.wowscrappie.notifications.domain.Notification;
import com.deswaef.wowscrappie.usermanagement.domain.ScrappieUser;
import com.deswaef.wowscrappie.usermanagement.util.AdministratorsCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

@Component
public class NotificationService {

    @Autowired
    private AdministratorsCache administratorsCache;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    public void putOnAdminQueue(Notification notification) {
        administratorsCache.getAdmins()
                .stream()
                .forEach(
                        admin -> messagingTemplate.convertAndSendToUser(admin.getUsername(), "/queue/notifications", notification)
                );
    }

    public void broadcastToAdmins(Notification notification) {
        administratorsCache.getAdmins()
                .stream()
                .forEach(
                        admin -> messagingTemplate.convertAndSendToUser(admin.getUsername(), "/topic/notifications", notification)
                );
    }

    public void broadcastTo(ScrappieUser user, Notification notification) {
        messagingTemplate.convertAndSendToUser(user.getUsername(), "/topic/notifications", notification);
    }

    public void broadcast(Notification notification) {
        messagingTemplate.convertAndSend("/topic/notifications", notification);
    }

}
