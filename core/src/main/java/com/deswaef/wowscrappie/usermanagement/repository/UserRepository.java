package com.deswaef.wowscrappie.usermanagement.repository;

import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;
import com.deswaef.wowscrappie.usermanagement.domain.ScrappieUser;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<ScrappieUser, Long> {
    Optional<ScrappieUser> findByUsername(@Param("username") String username);
    Optional<ScrappieUser> findByFacebookId(@Param("facebookId") String facebookId);
    Optional<ScrappieUser> findByTwitterId(@Param("twitterId") String twitterId);
    Optional<ScrappieUser> findByActivationCode(@Param("activationCode") String invitationCode);
    Optional<ScrappieUser> findByEmail(@Param("email") String email);
}
