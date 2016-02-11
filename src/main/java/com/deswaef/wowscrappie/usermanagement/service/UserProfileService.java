package com.deswaef.wowscrappie.usermanagement.service;


import com.deswaef.wowscrappie.usermanagement.controller.dto.UserProfileDto;
import com.deswaef.wowscrappie.usermanagement.domain.ScrappieUser;
import com.deswaef.wowscrappie.usermanagement.domain.UserProfile;

public interface UserProfileService {
    UserProfile findByUser(ScrappieUser user);
    UserProfile findByUser(Long userId);
    void update(UserProfileDto userProfileDto, ScrappieUser scrappieUser);
}
