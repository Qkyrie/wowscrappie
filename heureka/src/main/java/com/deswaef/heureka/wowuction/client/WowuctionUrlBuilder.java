package com.deswaef.heureka.wowuction.client;

import java.util.Optional;

public interface WowuctionUrlBuilder {
    Optional<String> build(Long realmId);
}
