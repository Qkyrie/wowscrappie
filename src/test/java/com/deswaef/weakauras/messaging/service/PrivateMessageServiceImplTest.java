package com.deswaef.weakauras.messaging.service;

import com.deswaef.weakauras.messaging.domain.PrivateMessage;
import com.deswaef.weakauras.messaging.repository.PrivateMessageRepository;
import com.deswaef.weakauras.security.SecurityUtility;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import com.deswaef.weakauras.usermanagement.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PrivateMessageServiceImplTest {

    @InjectMocks
    private PrivateMessageServiceImpl privateMessageService;

    @Mock
    private PrivateMessageRepository privateMessageRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private SecurityUtility securityUtility;

    @Mock
    private ScrappieUser admin;

    @Test
    public void send() {
        ArrayList<PrivateMessage> retValue = new ArrayList<>();
        when(privateMessageRepository.findByToUser(admin))
                .thenReturn(retValue);
        assertThat(privateMessageService.findMessagesToUser(admin))
                .isEqualTo(retValue);
    }


}