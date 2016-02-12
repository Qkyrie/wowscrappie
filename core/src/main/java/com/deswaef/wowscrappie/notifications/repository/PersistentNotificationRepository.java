package com.deswaef.wowscrappie.notifications.repository;

import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;
import com.deswaef.wowscrappie.notifications.domain.PersistentNotification;
import com.deswaef.wowscrappie.usermanagement.domain.ScrappieUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersistentNotificationRepository extends JpaRepository<PersistentNotification, Long>{

    List<PersistentNotification> findAllByForUserOrderByDateOfPostingDesc(@Param("forUser") ScrappieUser scrappieUser);

    @Query("select count(noti) from PersistentNotification noti where readStatus = false and forUser = :forUser")
    long countUnreadForUser(@Param("forUser")ScrappieUser scrappieUser);
}
