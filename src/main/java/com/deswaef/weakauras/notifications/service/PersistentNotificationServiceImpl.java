package com.deswaef.weakauras.notifications.service;

import com.deswaef.weakauras.notifications.controller.dto.PersistentNotificationDto;
import com.deswaef.weakauras.notifications.domain.Notification;
import com.deswaef.weakauras.notifications.domain.PersistentNotification;
import com.deswaef.weakauras.notifications.repository.PersistentNotificationRepository;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import com.deswaef.weakauras.usermanagement.util.AdministratorsCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersistentNotificationServiceImpl implements PersistentNotificationService {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private PersistentNotificationRepository persistentNotificationRepository;
    @Autowired
    private AdministratorsCache administratorsCache;

    @Transactional
    @Override
    public void notifyAdmins(PersistentNotificationDto persistentNotificationDto) {
        administratorsCache.getAdmins()
                .stream()
                .forEach(admin -> createPersistentNotification(admin, persistentNotificationDto));
    }

    @Transactional
    @Override
    public void createPersistentNotification(ScrappieUser scrappieUser, PersistentNotificationDto persistentNotificationDto) {
        persistentNotificationRepository.save(
                new PersistentNotification()
                        .setContent(persistentNotificationDto.getContent())
                        .setTitle(persistentNotificationDto.getTitle())
                        .setForUser(scrappieUser)
                        .setUrl(persistentNotificationDto.getUrl())
                        .setReadStatus(false)
        );
        notificationService.broadcastTo(scrappieUser, Notification.create(persistentNotificationDto.getTitle()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersistentNotification> findAll(ScrappieUser scrappieUser) {
        return persistentNotificationRepository.findAllByForUser(scrappieUser);
    }

    @Transactional(readOnly = true)
    @Override
    public long countAllUnread(ScrappieUser scrappieUser) {
        return persistentNotificationRepository.countUnreadForUser(scrappieUser);
    }

}
