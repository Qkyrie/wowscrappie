package com.deswaef.weakauras.notifications.service;

import com.deswaef.weakauras.notifications.domain.Notification;
import com.deswaef.weakauras.usermanagement.domain.RoleEnum;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import com.deswaef.weakauras.usermanagement.service.UserService;
import com.deswaef.weakauras.usermanagement.util.AdministratorsCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
