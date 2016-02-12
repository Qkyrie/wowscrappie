package com.deswaef.wowscrappie.message.repository;

import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;
import com.deswaef.wowscrappie.message.domain.AdminMessage;

public interface AdminMessageRepository extends JpaRepository<AdminMessage, Long> {
}
