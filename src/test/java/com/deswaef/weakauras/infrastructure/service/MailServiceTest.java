package com.deswaef.weakauras.infrastructure.service;

import com.deswaef.weakauras.WeakAuras;
import com.deswaef.weakauras.configuration.GreenmailConfiguration;
import com.icegreen.greenmail.util.GreenMail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.mail.Message;
import javax.mail.MessagingException;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WeakAuras.class)
@ActiveProfiles("integrationtest")
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class MailServiceTest {

    @Autowired
    private MailService mailService;
    @Autowired
    private GreenmailConfiguration.GreenmailServerBean greenmailServerBean;

    @Test
    public void sendBasicEmailWithFromAndTo() throws MessagingException {
        String mailText = "Hallo World";
        String mailSubject = "Hallo";
        String mailTo = "test@excaple.com";
        String mailFrom = "no-reply@wowscrappie.com";
        mailService.createMail()
                .body(mailText)
                .subject(mailSubject)
                .to(mailTo)
                .from(mailFrom)
                .send();

        GreenMail greenMail = greenmailServerBean.getGreenMail();
        boolean mailReceived = greenMail.waitForIncomingEmail(5000, 1);
        if (mailReceived) {
            Message[] messages = greenMail.getReceivedMessages();
            assertEquals(1, messages.length);
            assertEquals(mailSubject, messages[0].getSubject());
        }
    }


}