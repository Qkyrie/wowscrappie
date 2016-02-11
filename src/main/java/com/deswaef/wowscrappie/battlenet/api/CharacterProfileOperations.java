package com.deswaef.wowscrappie.battlenet.api;

import com.deswaef.wowscrappie.battlenet.api.domain.CharacterProfile;
import com.deswaef.wowscrappie.battlenet.api.domain.CharacterProfileFields;

public interface CharacterProfileOperations {

    CharacterProfile characterProfile(String realm, String characterName, CharacterProfileFields... fields);

}
