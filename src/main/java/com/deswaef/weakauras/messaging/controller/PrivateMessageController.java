package com.deswaef.weakauras.messaging.controller;

import com.deswaef.weakauras.messaging.controller.dto.PrivateMessageDetailsDto;
import com.deswaef.weakauras.messaging.controller.dto.PrivateMessageListDto;
import com.deswaef.weakauras.messaging.controller.dto.PrivateMessageReplyDto;
import com.deswaef.weakauras.messaging.domain.PrivateMessage;
import com.deswaef.weakauras.messaging.service.PrivateMessageService;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/personal/inbox")
public class PrivateMessageController {

    private static final PrettyTime prettyTime = new PrettyTime(Locale.ENGLISH);
    private static final Comparator<PrivateMessage> byDateForList = (pm1, pm2) -> pm1.getDateOfPosting().compareTo(pm2.getDateOfPosting());
    private static final Comparator<PrivateMessageListDto> byDate = (pm1, pm2) -> pm2.getActualDate().compareTo(pm1.getActualDate());

    @Autowired
    private PrivateMessageService privateMessageService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(method = GET)
    public String index(ModelMap modelMap) {
        modelMap.put("inbox", privateMessageService.findMessagesToUser(getThisUser())
                .stream()
                .map(PrivateMessageListDto::create)
                .map(pmdto -> {
                    Optional<Date> lastActionDateForPrivateMessage = privateMessageService.getLastActionDateForPrivateMessage(pmdto.getId());
                    if (lastActionDateForPrivateMessage.isPresent()) {
                        pmdto.setWhen(prettyTime.format(lastActionDateForPrivateMessage.get()));
                        pmdto.setActualDate(lastActionDateForPrivateMessage.get());
                    }
                    return pmdto ;
                })
                .sorted(byDate)
                .collect(Collectors.toList()));
        return "personal/inbox/index";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/message/{originalMessageId}", method = GET)
    public String getMessages(ModelMap modelMap, @PathVariable("originalMessageId") Long originalMessageId) {
        Optional<PrivateMessage> originalPM = getOriginalPM(originalMessageId);
        if(!originalPM.isPresent()) {
            return "personal/inbox/index :: message-not-found";
        } else {
            modelMap.put("allMessages", getAllMessages(originalMessageId));
            modelMap.put("originalPM", originalPM.get());
            return "personal/inbox/index :: message-details";
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/reply", method = RequestMethod.POST)
    public String replyTo(ModelMap modelMap, @RequestBody PrivateMessageReplyDto privateMessageReplyDto) {
        try {
            privateMessageService.replyTo(privateMessageReplyDto);
            Optional<PrivateMessage> originalPM = getOriginalPM(privateMessageReplyDto.getOriginalMessageId());
            if(!originalPM.isPresent()) {
                return "personal/inbox/index :: message-not-found";
            } else {
                modelMap.put("allMessages", getAllMessages(privateMessageReplyDto.getOriginalMessageId()));
                modelMap.put("originalPM", originalPM.get());
                return "personal/inbox/index :: message-details";
            }
        } catch (Exception ex) {
            return "personal/inbox/index :: message-not-found";
        }
    }

    private Optional<PrivateMessage> getOriginalPM(Long originalMessageId) {
        return privateMessageService.findOne(originalMessageId);
    }

    private List<PrivateMessageDetailsDto> getAllMessages(Long originalMessageId) {
        Optional<PrivateMessage> originalMessage = privateMessageService.findOne(originalMessageId);
        if (originalMessage.isPresent()) {
            return privateMessageService.findAllByRoot(originalMessageId)
                    .stream()
                    .sorted(byDateForList)
                    .map(pm -> new PrivateMessageDetailsDto()
                                    .setTitle(pm.getTitle())
                                    .setContent(pm.getContent())
                                    .setWhen(prettyTime.format(pm.getDateOfPosting()))
                                    .setInward(isInward(pm))
                                    .setSender(pm.getFromUser().getUsername())
                    )
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }


    private boolean isInward(PrivateMessage pm) {
         return (!pm.getFromUser().equals(getThisUser()));
    }

    private ScrappieUser getThisUser() {
        return (ScrappieUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
