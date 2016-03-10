package com.deswaef.heureka.wowuction.client;

import com.deswaef.wowscrappie.realm.domain.Realm;
import com.deswaef.wowscrappie.realm.service.RealmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Profile("!integrationtest")
public class RemoteWowuctionUrlBuilder implements WowuctionUrlBuilder {

    @Value("${com.deswaef.wowscrappie.wowuction.baseurl}")
    private String baseUrl;

    @Value("${com.deswaef.wowscrappie.wowuction.secret}")
    private String token;

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
