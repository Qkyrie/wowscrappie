package com.deswaef.weakauras.sounds.controller;

import com.deswaef.weakauras.security.CurrentUser;
import com.deswaef.weakauras.sounds.configuration.SoundStore;
import com.deswaef.weakauras.usermanagement.domain.RoleEnum;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
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

    @RequestMapping(value = "/notification", method = RequestMethod.GET)
    public
    @ResponseBody
    byte[] getNotificationSound(@CurrentUser ScrappieUser scrappieUser) {
        try {
            if (isAdmin(scrappieUser)) {
                Optional<File> randomFile = soundStore.getRandomFile();
                if (randomFile.isPresent()) {
                    return IOUtils.toByteArray(new FileInputStream(randomFile.get()));
                } else {
                    return null;
                }
            } else {
                return IOUtils.toByteArray(new FileInputStream(new File(this.getClass().getClassLoader().getResource("sounds/notification.ogg").toURI())));
            }
        } catch (Exception e) {
            return null;
        }
    }

    private boolean isAdmin(ScrappieUser scrappieUser) {
        return scrappieUser != null && scrappieUser.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals(RoleEnum.ADMIN_ROLE.getUserRole()));
    }
}
