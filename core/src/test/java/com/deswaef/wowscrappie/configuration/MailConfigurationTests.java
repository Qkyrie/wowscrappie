package com.deswaef.wowscrappie.configuration;

import com.deswaef.wowscrappie.repository.IntegrationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import static org.assertj.core.api.Assertions.assertThat;

public class MailConfigurationTests extends IntegrationTests {

    @Autowired
    private JavaMailSender javaMailSender;

    @Test
    public void exists() throws Exception {
        assertThat(javaMailSender).isNotNull();
    }

    @Test
    public void configIsOk() throws Exception {
        assertThat(this.javaMailSender instanceof JavaMailSenderImpl).isTrue();

        JavaMailSenderImpl javaMailSenderImpl = (JavaMailSenderImpl) this.javaMailSender;
        assertThat(javaMailSenderImpl.getHost())
                .isEqualTo("localhost");
        assertThat(javaMailSenderImpl.getPort())
                .isEqualTo(25);
    }
}