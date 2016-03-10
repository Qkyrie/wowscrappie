package com.deswaef.wowscrappie.battlenet.api.impl;

import com.deswaef.wowscrappie.battlenet.api.AbstractBattlenetOperations;
import com.deswaef.wowscrappie.battlenet.api.UserOperations;
import com.deswaef.wowscrappie.battlenet.api.domain.BattlenetProfile;
import com.deswaef.wowscrappie.battlenet.api.domain.WowProfile;
import org.springframework.web.client.RestTemplate;

public class UserTemplate extends AbstractBattlenetOperations implements UserOperations {

    private RestTemplate restTemplate;

    public UserTemplate(RestTemplate restTemplate, boolean isAuthorizedForUser) {
        super(isAuthorizedForUser);
        this.restTemplate = restTemplate;
    }

    @Override
    public BattlenetProfile getUserProfile() {
        return restTemplate.getForObject(buildUri("/account/user"), BattlenetProfile.class);
    }

    @Override
    public WowProfile getWowProfile() {
        return restTemplate.getForObject(buildUri("/wow/user/characters"), WowProfile.class);
    }
}
