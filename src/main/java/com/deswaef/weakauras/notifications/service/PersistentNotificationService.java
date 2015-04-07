package com.deswaef.weakauras.notifications.service;

import com.deswaef.weakauras.notifications.controller.dto.PersistentNotificationDto;
import com.deswaef.weakauras.notifications.domain.PersistentNotification;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;

import java.util.List;

public interface PersistentNotificationService {
    void notifyAdmins(PersistentNotificationDto persistentNotificationDto);
    void createPersistentNotification(ScrappieUser scrappieUser, PersistentNotificationDto persistentNotificationDto);
    List<PersistentNotification> findAll(ScrappieUser scrappieUser);
    long countAllUnread(ScrappieUser scrappieUser);
}
