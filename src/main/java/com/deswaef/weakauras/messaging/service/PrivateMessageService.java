package com.deswaef.weakauras.messaging.service;

import com.deswaef.weakauras.messaging.domain.PrivateMessage;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;

import java.util.List;

public interface PrivateMessageService {

    List<PrivateMessage> findMessagesToUser(ScrappieUser scrappieUser);

}
