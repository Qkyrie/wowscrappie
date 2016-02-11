package com.deswaef.wowscrappie.notifications.controller;

import com.deswaef.wowscrappie.notifications.controller.dto.PersistentNotificationDto;
import com.deswaef.wowscrappie.notifications.domain.PersistentNotification;
import com.deswaef.wowscrappie.notifications.service.PersistentNotificationService;
import com.deswaef.wowscrappie.security.CurrentUser;
import com.deswaef.wowscrappie.usermanagement.domain.ScrappieUser;
import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/notifications")
public class NotificationController {

    private static final PrettyTime prettyTime = new PrettyTime(Locale.ENGLISH);

    @Autowired
    private PersistentNotificationService persistentNotificationService;

    @RequestMapping(method = GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    public String index(ModelMap modelMap, @CurrentUser ScrappieUser scrappieUser) {
        modelMap.put("notifications", persistentNotificationService.findAll(scrappieUser));
        return "notifications/index";
    }

    @RequestMapping("/unread")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getUnreadNotificationFragment(ModelMap modelMap) {
        modelMap.put("notifications", unreadNotifications());
        return "notifications/dropdownPanel :: dropdownPanel";
    }

    @RequestMapping("/redirect/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String redirectFromNotification(@PathVariable("id") long id) {
        String redirectUrl = "#";
        Optional<PersistentNotification> byId = persistentNotificationService.findById(id);
        if (byId.isPresent()) {
            persistentNotificationService.setRead(byId.get().getId());
            redirectUrl = byId.get().getUrl();
        }
        return String.format("redirect:%s", redirectUrl);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping("/delete/{id}")
    public @ResponseBody
    ResponseEntity deleteNotification(@PathVariable("id") long id) {
        persistentNotificationService.delete(id);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

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

    @SubscribeMapping("/notifications/unread")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<PersistentNotificationDto> unreadNotifications() {
        Optional<ScrappieUser> scrappieUser = getScrappieUser();
        if (scrappieUser.isPresent()) {
            return persistentNotificationService.findAll(scrappieUser.get())
                    .stream()
                    .filter(x -> !x.isReadStatus())
                    .limit(4)
                    .map(noti -> PersistentNotificationDto.create()
                            .setContent(noti.getContent())
                            .setTitle(noti.getTitle())
                            .setUrl(noti.getUrl())
                            .setId(noti.getId())
                            .setPostDate(prettyTime.format(noti.getDateOfPosting())))
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
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
