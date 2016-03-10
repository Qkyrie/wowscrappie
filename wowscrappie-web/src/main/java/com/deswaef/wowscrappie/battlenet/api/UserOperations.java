package com.deswaef.wowscrappie.battlenet.api;

import com.deswaef.wowscrappie.battlenet.api.domain.BattlenetProfile;
import com.deswaef.wowscrappie.battlenet.api.domain.WowProfile;

public interface UserOperations {
    BattlenetProfile getUserProfile();
    WowProfile getWowProfile();
}
