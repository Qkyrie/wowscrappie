package com.deswaef.weakauras.battlenet.api;

import com.deswaef.weakauras.battlenet.api.domain.BattlenetProfile;
import com.deswaef.weakauras.battlenet.api.domain.WowProfile;

public interface UserOperations {
    BattlenetProfile getUserProfile();
    WowProfile getWowProfile();
}
