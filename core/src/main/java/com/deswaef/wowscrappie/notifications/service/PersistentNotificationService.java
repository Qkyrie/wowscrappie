package com.deswaef.wowscrappie.notifications.service;

import com.deswaef.wowscrappie.notifications.domain.PersistentNotification;
import com.deswaef.wowscrappie.notifications.service.dto.PersistentNotificationDto;
import com.deswaef.wowscrappie.usermanagement.domain.ScrappieUser;

import java.util.List;
import java.util.Optional;

public interface PersistentNotificationService {
    void notifyAdmins(PersistentNotificationDto persistentNotificationDto);

    void createPersistentNotification(ScrappieUser scrappieUser, PersistentNotificationDto persistentNotificationDto);

    List<PersistentNotification> findAll(ScrappieUser scrappieUser);

    long countAllUnread(ScrappieUser scrappieUser);

    Optional<PersistentNotification> findById(long id);

    void setRead(long id);

    void delete(long id);
}
