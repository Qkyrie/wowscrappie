package com.deswaef.weakauras.messaging.repository;

import com.deswaef.weakauras.infrastructure.repository.JpaRepository;
import com.deswaef.weakauras.messaging.domain.PrivateMessage;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
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
