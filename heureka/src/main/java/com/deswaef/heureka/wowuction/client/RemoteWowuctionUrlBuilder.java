package com.deswaef.heureka.wowuction.client;

import com.deswaef.wowscrappie.realm.domain.Realm;
import com.deswaef.wowscrappie.realm.service.RealmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Profile("!integrationtest")
public class RemoteWowuctionUrlBuilder implements WowuctionUrlBuilder {

    private static String baseUrl = "http://www.wowuction.com/%s/%s/alliance/Tools/RealmDataExportGetFileStatic?token=%s";
    private static final String token = "gS-aRrCqjWs43TwaDPdvVQ2";

    @Autowired
    private RealmService realmService;

    @Override
    public Optional<String> build(Long realmId) {
        Optional<Realm> one = realmService.findOne(realmId);
        if (one.isPresent()) {
            Realm realmDto = one.get();
            return Optional.of(String.format(baseUrl, realmDto.getLocality().getLocalityName(), realmDto.getName().toLowerCase(), token));
        } else {
            return Optional.empty();
        }
    }

}
