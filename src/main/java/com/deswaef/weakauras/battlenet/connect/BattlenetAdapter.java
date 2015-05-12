package com.deswaef.weakauras.battlenet.connect;

import com.deswaef.weakauras.battlenet.api.Battlenet;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

public class BattlenetAdapter implements ApiAdapter<Battlenet>{
    @Override
    public boolean test(Battlenet api) {
        return false;
    }

    @Override
    public void setConnectionValues(Battlenet api, ConnectionValues values) {

    }

    @Override
    public UserProfile fetchUserProfile(Battlenet api) {
        return null;
    }

    @Override
    public void updateStatus(Battlenet api, String message) {

    }
}
