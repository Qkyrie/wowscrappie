package com.deswaef.wowscrappie.messaging.repository;

import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;
import com.deswaef.wowscrappie.messaging.domain.PrivateMessage;
import com.deswaef.wowscrappie.usermanagement.domain.ScrappieUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PrivateMessageRepository extends JpaRepository<PrivateMessage, Long>{
    List<PrivateMessage> findByToUser(@Param("toUser") ScrappieUser toUser);
    List<PrivateMessage> findByResponseTo(@Param("responseTo") PrivateMessage responseTo);

    @Query("select max(dateOfPosting) from PrivateMessage pm where id = :id or responseTo.id = :id")
    Optional<Date> findMaxDateForRootMessage(@Param("id") Long id);
}
