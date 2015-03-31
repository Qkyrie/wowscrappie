package com.deswaef.weakauras.usermanagement.repository;

import com.deswaef.weakauras.infrastructure.repository.JpaRepository;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import com.deswaef.weakauras.usermanagement.domain.UserProfile;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{

    Optional<UserProfile> findByScrappieUser(@Param("scrappieUser") ScrappieUser scrappieUser);

}
