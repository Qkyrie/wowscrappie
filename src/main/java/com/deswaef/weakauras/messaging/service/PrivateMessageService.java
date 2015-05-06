package com.deswaef.weakauras.messaging.service;

import com.deswaef.weakauras.messaging.controller.dto.PrivateMessageReplyDto;
import com.deswaef.weakauras.messaging.domain.PrivateMessage;
import com.deswaef.weakauras.mvc.dto.ContactRequestDto;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;

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
