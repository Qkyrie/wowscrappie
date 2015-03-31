package com.deswaef.weakauras.usermanagement.service;


import com.deswaef.weakauras.usermanagement.controller.dto.UserProfileDto;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import com.deswaef.weakauras.usermanagement.domain.UserProfile;

public interface UserProfileService {
    UserProfile findByUser(ScrappieUser user);
    UserProfile findByUser(Long userId);
    void update(UserProfileDto userProfileDto, ScrappieUser scrappieUser);
}
