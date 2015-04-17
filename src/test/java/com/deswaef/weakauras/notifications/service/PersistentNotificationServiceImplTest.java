package com.deswaef.weakauras.notifications.service;

import com.deswaef.weakauras.notifications.controller.dto.PersistentNotificationDto;
import com.deswaef.weakauras.notifications.domain.Notification;
import com.deswaef.weakauras.notifications.domain.PersistentNotification;
import com.deswaef.weakauras.notifications.repository.PersistentNotificationRepository;
import com.deswaef.weakauras.security.SecurityUtility;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import com.deswaef.weakauras.usermanagement.util.AdministratorsCache;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Persistent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersistentNotificationServiceImplTest {

    public static final String URL = "URL";
    public static final String CONTENT = "CONTENT";
    public static final String TITLE = "TITLE";
    public static final long ID = 1L;
    @InjectMocks
    private PersistentNotificationServiceImpl persistentNotificationService;
    @Mock
    private PersistentNotificationRepository persistentNotificationRepository;
    @Mock
    private AdministratorsCache administratorsCache;
    @Mock
    private SecurityUtility securityUtility;
    @Mock
    private NotificationService notificationService;

    @Mock
    private ScrappieUser admin;
    private PersistentNotificationDto persistentNotificationDto;

    @Mock
    private PersistentNotification persistentNotification = new PersistentNotification();

    @Before
    public void setUp() {
        when(administratorsCache.getAdmins())
                .thenReturn(new HashSet<>(Arrays.asList(admin)));
        persistentNotificationDto = PersistentNotificationDto.create()
                .setUrl(URL)
                .setContent(CONTENT)
                .setTitle(TITLE)
                .setId(ID);
    }


    @Test
    public void notifyAdminsCreatesBroadcastsAndSavesNotifications() {
        ArgumentCaptor<PersistentNotification> persistentNotificationArgumentCaptor = ArgumentCaptor.forClass(PersistentNotification.class);
        ArgumentCaptor<Notification> notificationArgumentCaptor = ArgumentCaptor.forClass(Notification.class);
        persistentNotificationService.notifyAdmins(persistentNotificationDto);

        verify(persistentNotificationRepository).save(persistentNotificationArgumentCaptor.capture());
        verify(notificationService).broadcastTo(eq(admin), notificationArgumentCaptor.capture());

        PersistentNotification capturedPersistentNotification = persistentNotificationArgumentCaptor.getValue();
        assertThat(capturedPersistentNotification.getTitle()).isEqualTo(TITLE);
        assertThat(capturedPersistentNotification.getForUser()).isEqualTo(admin);
        assertThat(capturedPersistentNotification.getUrl()).isEqualTo(URL);
        assertThat(capturedPersistentNotification.isReadStatus()).isEqualTo(false);
        assertThat(capturedPersistentNotification.getContent()).isEqualTo(CONTENT);

        Notification capturedNotification = notificationArgumentCaptor.getValue();
        assertThat(capturedNotification.getMessage()).isEqualTo(TITLE);
    }

    @Test
    public void findAllCallsRepository() {
        when(persistentNotificationRepository.findAllByForUserOrderByDateOfPostingDesc(admin))
                .thenReturn(Arrays.asList(persistentNotification));
        List<PersistentNotification> all = persistentNotificationService.findAll(admin);
        assertThat(all).contains(persistentNotification);
    }

    @Test
    public void countUnreadForUserCallsRepository() {
        long expected = 1L;
        when(persistentNotificationRepository.countUnreadForUser(admin))
                .thenReturn(expected);
        assertThat(persistentNotificationService.countAllUnread(admin))
                .isEqualTo(expected);
    }

    @Test
    public void findByIdAndExists() {
        when(persistentNotificationRepository.findOne(1L))
                .thenReturn(Optional.of(persistentNotification));
        assertThat(persistentNotificationService.findById(1L).get())
                .isEqualTo(persistentNotification);
    }

    @Test
    public void findByIdAndDoesntExist() {
        when(persistentNotificationRepository.findOne(1L))
                .thenReturn(Optional.empty());
        assertThat(persistentNotificationService.findById(1L).isPresent())
                .isFalse();
    }

    @Test
    public void setReadUpdatesWithReadValue() {
        when(persistentNotificationRepository.findOne(ID))
                .thenReturn(Optional.of(persistentNotification));
        persistentNotificationService.setRead(ID);
    }

}