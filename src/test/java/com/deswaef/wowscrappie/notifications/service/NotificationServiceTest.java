package com.deswaef.wowscrappie.notifications.service;


import com.deswaef.wowscrappie.notifications.domain.Notification;
import com.deswaef.wowscrappie.usermanagement.domain.ScrappieUser;
import com.deswaef.wowscrappie.usermanagement.util.AdministratorsCache;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

import java.util.Arrays;
import java.util.HashSet;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NotificationServiceTest {

    public static final String ADMIN = "ADMIN";
    public static final String QUEUE_NOTIFICATIONS = "/queue/notifications";
    public static final String TOPIC_NOTIFICATIONS = "/topic/notifications";
    @InjectMocks
    private NotificationService notificationService;
    @Mock
    private AdministratorsCache administratorsCache;
    @Mock
    private SimpMessageSendingOperations messagingTemplate;

    @Mock
    private Notification notification;
    @Mock
    private ScrappieUser admin;

    @Before
    public void init() {
        when(admin.getUsername())
                .thenReturn(ADMIN);
        when(administratorsCache.getAdmins())
                .thenReturn(new HashSet<>(Arrays.asList(admin)));
    }

    @Test
    public void putOnAdminQueueFetchesAdminsAndPutsItOnTheirQueue() {
        notificationService.putOnAdminQueue(notification);
        verify(messagingTemplate).convertAndSendToUser(ADMIN, QUEUE_NOTIFICATIONS, notification);
    }

    @Test
    public void broadcastToAdmins() {
        notificationService.broadcastToAdmins(notification);
        verify(messagingTemplate).convertAndSendToUser(ADMIN, TOPIC_NOTIFICATIONS, notification);
    }

    @Test
    public void simpleBroadcastTo(){
        notificationService.broadcastTo(admin, notification);
        verify(messagingTemplate).convertAndSendToUser(ADMIN, TOPIC_NOTIFICATIONS, notification);
    }

    @Test
    public void simpleBroadcastToAllOnline() {
        notificationService.broadcast(notification);
        verify(messagingTemplate).convertAndSend(TOPIC_NOTIFICATIONS, notification);
    }

}