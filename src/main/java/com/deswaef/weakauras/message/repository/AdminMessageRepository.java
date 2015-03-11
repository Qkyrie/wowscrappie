package com.deswaef.weakauras.message.repository;

import com.deswaef.weakauras.infrastructure.repository.JpaRepository;
import com.deswaef.weakauras.message.domain.AdminMessage;

public interface AdminMessageRepository extends JpaRepository<AdminMessage, Long> {
}
