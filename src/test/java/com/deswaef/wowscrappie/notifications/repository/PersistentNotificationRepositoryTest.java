package com.deswaef.wowscrappie.notifications.repository;

import com.deswaef.wowscrappie.repository.RepositoryIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.fest.assertions.Assertions.assertThat;

public class PersistentNotificationRepositoryTest extends RepositoryIntegrationTest {

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