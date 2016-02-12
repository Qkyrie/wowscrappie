package com.deswaef.wowscrappie.usermanagement.repository;

import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;
import com.deswaef.wowscrappie.usermanagement.domain.ScrappieUser;
import com.deswaef.wowscrappie.usermanagement.domain.UserProfile;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{

    Optional<UserProfile> findByScrappieUser(@Param("scrappieUser") ScrappieUser scrappieUser);

}
