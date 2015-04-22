package com.deswaef.weakauras.usermanagement.service;

import com.deswaef.weakauras.sounds.domain.SoundRepositoryEnum;
import com.deswaef.weakauras.usermanagement.controller.dto.UserProfileDto;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import com.deswaef.weakauras.usermanagement.domain.UserProfile;
import com.deswaef.weakauras.usermanagement.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public UserProfile findByUser(ScrappieUser user) {
        Optional<UserProfile> byScrappieUser = userProfileRepository.findByScrappieUser(user);
        if (!byScrappieUser.isPresent()) {
            return createNewProfile(user);
        } else {
            return byScrappieUser.get();
        }
    }

    private UserProfile createNewProfile(ScrappieUser user) {
        return userProfileRepository.save(
                new UserProfile()
                        .setScrappieUser(user)
                        .setAboutMe("")
                        .setTwitchStream("")
                        .setTwitterName("")
        );
    }

    @Override
    @Transactional
    public UserProfile findByUser(Long userId) {
        Optional<ScrappieUser> byId = userService.findById(userId);
        if(!byId.isPresent()) {
            throw new IllegalArgumentException("That user was not found");
        } else {
            return findByUser(byId.get());
        }
    }

    @Override
    @Transactional
    public void update(UserProfileDto userProfileDto, ScrappieUser scrappieUser) {
        if (userProfileDto.getSoundRepository() == null) {
            userProfileDto.setSoundRepository(SoundRepositoryEnum.DEFAULT);
        }

        UserProfile byUser = findByUser(scrappieUser);
        UserProfile entity = byUser
                .setAboutMe(userProfileDto.getAboutMe())
                .setTwitterName(userProfileDto.getTwitterName())
                .setTwitchStream(userProfileDto.getTwitchStream())
                .setSoundRepositoryEnum(userProfileDto.getSoundRepository())
                .setReceiveEmailNotifications(userProfileDto.isReceiveEmailNotifications());
        userProfileRepository.save(
                entity
        );
    }
}
