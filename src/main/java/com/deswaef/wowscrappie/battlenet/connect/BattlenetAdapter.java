package com.deswaef.wowscrappie.battlenet.connect;

import com.deswaef.wowscrappie.battlenet.api.Battlenet;
import com.deswaef.wowscrappie.battlenet.api.domain.BattlenetProfile;
import com.google.common.base.Strings;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;

public class BattlenetAdapter implements ApiAdapter<Battlenet>{
    @Override
    public boolean test(Battlenet api) {
        try {
            BattlenetProfile battlenetProfile = api.userOperations().getUserProfile();
            if (battlenetProfile.getId() != null && !Strings.isNullOrEmpty(battlenetProfile.getBattletag())) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public void setConnectionValues(Battlenet api, ConnectionValues values) {
        BattlenetProfile userProfile = api.userOperations().getUserProfile();
        values.setDisplayName(userProfile.getBattletag());
        values.setProviderUserId(String.valueOf(userProfile.getId()));
    }

    @Override
    public UserProfile fetchUserProfile(Battlenet api) {
        BattlenetProfile userProfile = api.userOperations().getUserProfile();
        return new UserProfileBuilder().setUsername(userProfile.getBattletag()).build();
    }

    @Override
    public void updateStatus(Battlenet api, String message) {
        //we don't update statuses in battlenet
    }
}
