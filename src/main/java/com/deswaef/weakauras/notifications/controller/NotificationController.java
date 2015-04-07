package com.deswaef.weakauras.notifications.controller;

import com.deswaef.weakauras.notifications.service.PersistentNotificationService;
import com.deswaef.weakauras.security.CurrentUser;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private PersistentNotificationService persistentNotificationService;

    @SubscribeMapping("/notifications/amount")
    @PreAuthorize("hasRole('ROLE_USER')")
    public long getPersonalNotificationCount() {
        Optional<ScrappieUser> scrappieUser = getScrappieUser();
        if (scrappieUser.isPresent()) {
            return persistentNotificationService.countAllUnread(scrappieUser.get());
        } else {
            return 0;
        }
    }

    private Optional<ScrappieUser> getScrappieUser() {
        if (SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof ScrappieUser) {
            return Optional.of((ScrappieUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        } else {
            return Optional.empty();
        }
    }


}
