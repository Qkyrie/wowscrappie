package com.deswaef.wowscrappie.usermanagement.service;


import com.deswaef.wowscrappie.usermanagement.domain.ScrappieUser;
import com.deswaef.wowscrappie.usermanagement.domain.UserProfile;
import com.deswaef.wowscrappie.usermanagement.service.dto.UserProfileDto;

public interface UserProfileService {
    UserProfile findByUser(ScrappieUser user);

    UserProfile findByUser(Long userId);

    void update(UserProfileDto userProfileDto, ScrappieUser scrappieUser);
}
