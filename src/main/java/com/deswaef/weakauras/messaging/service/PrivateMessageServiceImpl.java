package com.deswaef.weakauras.messaging.service;

import com.deswaef.weakauras.messaging.controller.dto.PrivateMessageReplyDto;
import com.deswaef.weakauras.messaging.domain.PrivateMessage;
import com.deswaef.weakauras.messaging.repository.PrivateMessageRepository;
import com.deswaef.weakauras.mvc.dto.ContactRequestDto;
import com.deswaef.weakauras.security.SecurityUtility;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import com.deswaef.weakauras.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PrivateMessageServiceImpl implements PrivateMessageService {

    public static final long PIKA_ID = 1L;
    @Autowired
    private PrivateMessageRepository privateMessageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SecurityUtility securityUtility;


    @Override
    @Transactional(readOnly = true)
    public List<PrivateMessage> findMessagesToUser(ScrappieUser scrappieUser) {
        List<PrivateMessage> messagesToUser = privateMessageRepository.findByToUser(scrappieUser);
        List<PrivateMessage> returnValue = new ArrayList<>();
        for (PrivateMessage privateMessage : messagesToUser) {
            if (privateMessage.getResponseTo() != null) {
                if(!returnValue.contains(privateMessage.getResponseTo())){
                    returnValue.add(privateMessage.getResponseTo());
                }
            } else {
                returnValue.add(privateMessage);
            }
        }
        return returnValue;
    }

    @Override
    @Transactional
    public void sendToPika(ContactRequestDto contactRequestDto) {
        Optional<ScrappieUser> pikachu = userRepository.findOne(PIKA_ID);
        Optional<ScrappieUser> currentUser = securityUtility.currentUser();
        if (pikachu.isPresent() && currentUser.isPresent()) {
            try {
            privateMessageRepository.save(new PrivateMessage()
                            .setContent(contactRequestDto.getContent())
                            .setDateOfPosting(now())
                            .setFromUser(currentUser.get())
                            .setToUser(pikachu.get())
                            .setResponseTo(null)
                            .setTitle(contactRequestDto.getTitle()));
            } catch(Exception ex) {
                throw new IllegalArgumentException("Unable to send the message :(");
            }
        } else {
            throw new IllegalArgumentException("Unable to send the message, make sure you're logged in -> Error 69");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<PrivateMessage> findAllByRoot(Long rootMessageId) {
        List<PrivateMessage> returnValue = new ArrayList<>();
        Optional<PrivateMessage> rootMessage = privateMessageRepository.findOne(rootMessageId);
        if (rootMessage.isPresent()) {
            List<PrivateMessage> byResponseTo = privateMessageRepository.findByResponseTo(rootMessage.get());
            for (PrivateMessage privateMessage : byResponseTo) {
                returnValue.addAll(findAllByRoot(privateMessage.getId()));
            }
            returnValue.add(rootMessage.get());
            return returnValue;
        } else {
            return returnValue;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PrivateMessage> findOne(Long id) {
        return privateMessageRepository.findOne(id);
    }

    @Override
    @Transactional
    public void replyTo(PrivateMessageReplyDto privateMessageReplyDto) {
        Optional<PrivateMessage> originalMessage = privateMessageRepository.findOne(privateMessageReplyDto.getOriginalMessageId());
        Optional<ScrappieUser> currentUser = securityUtility.currentUser();
        if (originalMessage.isPresent() && currentUser.isPresent() && isOneOfBoth(currentUser.get(), originalMessage.get())) {
            privateMessageRepository.save(new PrivateMessage()
                            .setTitle(originalMessage.get().getTitle())
                            .setContent(privateMessageReplyDto.getContent())
                            .setResponseTo(originalMessage.get())
                            .setDateOfPosting(now())
                            .setFromUser(currentUser.get())
                            .setToUser(
                                    getToUser(originalMessage, currentUser)
                            )
            );
        } else {
            throw new IllegalArgumentException("That original message was not found");
        }
    }

    private boolean isOneOfBoth(ScrappieUser user, PrivateMessage privateMessage) {
        return privateMessage.getFromUser().equals(user) || privateMessage.getToUser().equals(user);
    }

    private ScrappieUser getToUser(Optional<PrivateMessage> originalMessage, Optional<ScrappieUser> currentUser) {
        if (currentUser.get().equals(originalMessage.get().getFromUser())) {
            return originalMessage.get().getToUser();
        } else {
            return originalMessage.get().getFromUser();
        }
    }

    private Date now() {
        return new Date();
    }
}
