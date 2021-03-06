package com.deswaef.wowscrappie.battlenet.connect;

import com.deswaef.wowscrappie.battlenet.api.Battlenet;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

public class BattlenetConnectionFactory extends OAuth2ConnectionFactory<Battlenet> {

    public BattlenetConnectionFactory(String appId, String appSecret) {
        super("battlenet", new BattlenetServiceProvider(appId, appSecret), new BattlenetAdapter());
    }

}
