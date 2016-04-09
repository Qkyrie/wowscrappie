package com.deswaef.wowscrappie.notifications.repository;

import com.deswaef.wowscrappie.repository.IntegrationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;


public class PersistentNotificationTests extends IntegrationTests {

    @Autowired
    private PersistentNotificationRepository persistentNotificationRepository;

    @Test
    public void findAllReturnsAList() {
        assertThat(persistentNotificationRepository.findAll())
                .isEmpty();
    }

    @Test
    public void unknownReturnsEmptyOptional() {
        assertThat(persistentNotificationRepository.findOne(1L).isPresent())
                .isFalse();
    }
}