package com.deswaef.wowscrappie.sounds.controller;

import com.deswaef.wowscrappie.security.CurrentUser;
import com.deswaef.wowscrappie.sounds.configuration.SoundStore;
import com.deswaef.wowscrappie.sounds.domain.SoundRepositoryEnum;
import com.deswaef.wowscrappie.usermanagement.domain.ScrappieUser;
import com.deswaef.wowscrappie.usermanagement.domain.UserProfile;
import com.deswaef.wowscrappie.usermanagement.service.UserProfileService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.util.Optional;

@Controller
@RequestMapping("/shared/sound")
public class SoundController {

    @Autowired
    private SoundStore soundStore;
    @Autowired
    private UserProfileService userProfileService;

    @RequestMapping(value = "/notification", method = RequestMethod.GET)
    @ResponseBody
    public byte[] getNotificationSound(@CurrentUser ScrappieUser scrappieUser) {
        try {
            SoundRepositoryEnum soundRepo = getSoundRepo(scrappieUser);
            Optional<File> randomFile = soundStore.getRandomFile(soundRepo);
            if (randomFile.isPresent()) {
                return IOUtils.toByteArray(new FileInputStream(randomFile.get()));
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    private SoundRepositoryEnum getSoundRepo(ScrappieUser scrappieUser) {
        if (scrappieUser != null) {
            UserProfile byUser = userProfileService.findByUser(scrappieUser);
            return byUser.getSoundRepositoryEnum();
        } else {
            return SoundRepositoryEnum.DEFAULT;
        }
    }
}
