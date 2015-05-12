package com.deswaef.weakauras.battlenet.connect;

import com.deswaef.weakauras.battlenet.api.Battlenet;
import com.deswaef.weakauras.battlenet.api.BattlenetTemplate;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

public class BattlenetServiceProvider extends AbstractOAuth2ServiceProvider<Battlenet> {

    private String appId;

    public BattlenetServiceProvider(String appId, String appSecret) {
        super(getOauth2Template(appId, appSecret));
        this.appId = appId;
    }

    private static OAuth2Template getOauth2Template(String appId, String appSecret) {
        OAuth2Template oAuth2Template = new OAuth2Template(appId, appSecret, "https://eu.battle.net/oauth/authorize",
                "https://eu.battle.net/oauth/token");
        oAuth2Template.setUseParametersForClientAuthentication(true);
        return oAuth2Template;
    }

    @Override
    public Battlenet getApi(String accessToken) {
        return new BattlenetTemplate(accessToken, appId);
    }
}
