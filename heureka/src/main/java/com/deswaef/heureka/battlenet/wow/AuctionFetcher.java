package com.deswaef.heureka.battlenet.wow;

import com.deswaef.heureka.battlenet.wow.auctions.client.domain.AuctionResponse;
import com.deswaef.heureka.battlenet.wow.client.BattlenetAPIClient;
import com.deswaef.heureka.battlenet.wow.domain.AuctionSnapshot;
import com.deswaef.wowscrappie.realm.domain.Realm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuctionFetcher {

    public static final String API_AUCTIONS_ROOT = "/auction/data/";
    @Autowired
    private BattlenetAPIClient battlenetAPIClient;

    public AuctionResponse getLatestAuctionInformation(Realm realm) {
        return AuctionResponse.fromJson(battlenetAPIClient.getFromWowAPI(String.format("%s%s", API_AUCTIONS_ROOT, realm.getSlug()), realm.getLocality()));
    }

    public AuctionSnapshot getLatestSnapshot(String effectiveUrl) {
        return AuctionSnapshot.fromJson(battlenetAPIClient.getFromUrl(effectiveUrl));
    }
}