package com.deswaef.wowscrappie.messaging.service;

import com.deswaef.wowscrappie.messaging.controller.dto.PrivateMessageReplyDto;
import com.deswaef.wowscrappie.messaging.domain.PrivateMessage;
import com.deswaef.wowscrappie.mvc.dto.ContactRequestDto;
import com.deswaef.wowscrappie.usermanagement.domain.ScrappieUser;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PrivateMessageService {

    List<PrivateMessage> findMessagesToUser(ScrappieUser scrappieUser);

    Optional<Date> getLastActionDateForPrivateMessage(Long privateMessage);

    void sendToPika(ContactRequestDto contactRequestDto);

    List<PrivateMessage> findAllByRoot(Long rootMessageId);

    Optional<PrivateMessage> findOne(Long id);

    void replyTo(PrivateMessageReplyDto privateMessageReplyDto);

    void sendtoUser(ContactRequestDto contactRequestDto);
}
