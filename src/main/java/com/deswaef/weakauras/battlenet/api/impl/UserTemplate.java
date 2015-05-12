package com.deswaef.weakauras.battlenet.api.impl;

import com.deswaef.weakauras.battlenet.api.UserOperations;
import com.deswaef.weakauras.battlenet.api.UserProfile;
import org.springframework.web.client.RestTemplate;

public class UserTemplate implements UserOperations {

    private RestTemplate restTemplate;

    public UserTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public UserProfile getUserProfile() {
        return restTemplate.getForObject("https://eu.api.battle.net/account/user", UserProfile.class);
    }
}
