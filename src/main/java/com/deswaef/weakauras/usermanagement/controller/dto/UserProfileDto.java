package com.deswaef.weakauras.usermanagement.controller.dto;

import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import com.deswaef.weakauras.usermanagement.domain.UserProfile;

public class UserProfileDto {
    private Long userId;
    private String userName;
    private String twitchStream;
    private String twitterName;
    private String aboutMe;
    private Long macros;
    private Long tmws;
    private Long was;

    public static UserProfileDto create(ScrappieUser user, UserProfile profile) {
        return new UserProfileDto()
                .setAboutMe(profile.getAboutMe())
                .setUserName(user.getUsername())
                .setTwitchStream(profile.getTwitchStream())
                .setTwitterName(profile.getTwitterName())
                .setUserId(user.getId());
    }

    public String getUserName() {
        return userName;
    }

    public UserProfileDto setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getTwitchStream() {
        return twitchStream;
    }

    public UserProfileDto setTwitchStream(String twitchStream) {
        this.twitchStream = twitchStream;
        return this;
    }

    public String getTwitterName() {
        return twitterName;
    }

    public UserProfileDto setTwitterName(String twitterName) {
        this.twitterName = twitterName;
        return this;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public UserProfileDto setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
        return this;
    }

    public Long getMacros() {
        return macros;
    }

    public UserProfileDto setMacros(Long macros) {
        this.macros = macros;
        return this;
    }

    public Long getTmws() {
        return tmws;
    }

    public UserProfileDto setTmws(Long tmws) {
        this.tmws = tmws;
        return this;
    }

    public Long getWas() {
        return was;
    }

    public UserProfileDto setWas(Long was) {
        this.was = was;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public UserProfileDto setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
}
