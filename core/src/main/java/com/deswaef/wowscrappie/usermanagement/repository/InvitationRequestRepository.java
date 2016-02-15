package com.deswaef.wowscrappie.usermanagement.repository;

import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;
import com.deswaef.wowscrappie.usermanagement.domain.InvitationRequest;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface InvitationRequestRepository extends JpaRepository<InvitationRequest, Long> {

    Optional<InvitationRequest> findByEmail(@Param("email") String email);

}
