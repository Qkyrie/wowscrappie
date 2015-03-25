package com.deswaef.weakauras.messaging.repository;

import com.deswaef.weakauras.infrastructure.repository.JpaRepository;
import com.deswaef.weakauras.messaging.domain.PrivateMessage;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PrivateMessageRepository extends JpaRepository<PrivateMessage, Long>{
    List<PrivateMessage> findByToUser(@Param("toUser") ScrappieUser toUser);

    List<PrivateMessage> findByResponseTo(@Param("responseTo") PrivateMessage responseTo);
}
