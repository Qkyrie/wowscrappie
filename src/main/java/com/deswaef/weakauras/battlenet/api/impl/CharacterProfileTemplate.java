package com.deswaef.weakauras.battlenet.api.impl;

import com.deswaef.weakauras.battlenet.api.AbstractBattlenetOperations;
import com.deswaef.weakauras.battlenet.api.CharacterProfileOperations;
import com.deswaef.weakauras.battlenet.api.domain.CharacterProfile;
import com.deswaef.weakauras.battlenet.api.domain.CharacterProfileFields;
import org.springframework.web.client.RestTemplate;

public class CharacterProfileTemplate extends AbstractBattlenetOperations implements CharacterProfileOperations {

    private RestTemplate restTemplate;

    public CharacterProfileTemplate(RestTemplate restTemplate, boolean isAuthorized) {
        super(isAuthorized);
        this.restTemplate = restTemplate;
    }


    @Override
    public CharacterProfile characterProfile(String realm, String characterName, CharacterProfileFields... fields) {
        return null;
    }
}
