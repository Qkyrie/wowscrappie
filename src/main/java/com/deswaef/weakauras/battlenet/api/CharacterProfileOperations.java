package com.deswaef.weakauras.battlenet.api;

import com.deswaef.weakauras.battlenet.api.domain.CharacterProfile;
import com.deswaef.weakauras.battlenet.api.domain.CharacterProfileFields;

public interface CharacterProfileOperations {

    CharacterProfile characterProfile(String realm, String characterName, CharacterProfileFields... fields);

}
