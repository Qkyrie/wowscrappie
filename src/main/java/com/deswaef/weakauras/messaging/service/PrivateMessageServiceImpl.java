package com.deswaef.weakauras.messaging.service;

import com.deswaef.weakauras.messaging.domain.PrivateMessage;
import com.deswaef.weakauras.messaging.repository.PrivateMessageRepository;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PrivateMessageServiceImpl implements PrivateMessageService {

    @Autowired
    private PrivateMessageRepository privateMessageRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PrivateMessage> findMessagesToUser(ScrappieUser scrappieUser) {
        return privateMessageRepository.findByToUser(scrappieUser);
    }
}
