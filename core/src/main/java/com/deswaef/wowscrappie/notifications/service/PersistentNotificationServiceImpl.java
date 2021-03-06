package com.deswaef.wowscrappie.notifications.service;

import com.deswaef.wowscrappie.notifications.domain.Notification;
import com.deswaef.wowscrappie.notifications.domain.PersistentNotification;
import com.deswaef.wowscrappie.notifications.repository.PersistentNotificationRepository;
import com.deswaef.wowscrappie.notifications.service.dto.PersistentNotificationDto;
import com.deswaef.wowscrappie.security.SecurityUtility;
import com.deswaef.wowscrappie.usermanagement.domain.ScrappieUser;
import com.deswaef.wowscrappie.usermanagement.util.AdministratorsCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PersistentNotificationServiceImpl implements PersistentNotificationService {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private PersistentNotificationRepository persistentNotificationRepository;
    @Autowired
    private AdministratorsCache administratorsCache;
    @Autowired
    private SecurityUtility securityUtility;

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
                        .setDateOfPosting(now())
        );
        notificationService.broadcastTo(scrappieUser, Notification.create(persistentNotificationDto.getTitle()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersistentNotification> findAll(ScrappieUser scrappieUser) {
        return persistentNotificationRepository.findAllByForUserOrderByDateOfPostingDesc(scrappieUser);
    }

    @Transactional(readOnly = true)
    @Override
    public long countAllUnread(ScrappieUser scrappieUser) {
        return persistentNotificationRepository.countUnreadForUser(scrappieUser);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PersistentNotification> findById(long id) {
        return persistentNotificationRepository.findOne(id);
    }

    @Override
    @Transactional
    public void setRead(long id) {
        Optional<PersistentNotification> one = persistentNotificationRepository.findOne(id);
        if (one.isPresent()) {
            persistentNotificationRepository.save(
                    one.get().setReadStatus(true)
            );
        }
    }

    @Override
    @Transactional
    public void delete(long id) {
        Optional<PersistentNotification> one = persistentNotificationRepository.findOne(id);
        Optional<ScrappieUser> currentUser = securityUtility.currentUser();
        if (one.isPresent() && currentUser.isPresent() && currentUser.get().equals(one.get().getForUser())) {
            persistentNotificationRepository.delete(one.get());
        }
    }

    private Date now() {
        return new Date();
    }
}
