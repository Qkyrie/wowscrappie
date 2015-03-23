package com.deswaef.weakauras.usermanagement.repository;

import com.deswaef.weakauras.infrastructure.repository.JpaRepository;
import com.deswaef.weakauras.usermanagement.domain.InvitationRequest;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface InvitationRequestRepository extends JpaRepository<InvitationRequest, Long>{

    Optional<InvitationRequest> findByEmail(@Param("email") String email);

}
