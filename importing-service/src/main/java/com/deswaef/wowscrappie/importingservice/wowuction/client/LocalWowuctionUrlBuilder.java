package com.deswaef.wowscrappie.importingservice.wowuction.client;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Profile("integrationtest")
public class LocalWowuctionUrlBuilder implements WowuctionUrlBuilder {
    @Override
    public Optional<String> build(Long realmId) {
        return null;
    }
}
