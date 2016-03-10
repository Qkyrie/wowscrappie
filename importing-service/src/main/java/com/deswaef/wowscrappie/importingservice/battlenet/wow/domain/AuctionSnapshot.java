package com.deswaef.wowscrappie.importingservice.battlenet.wow.domain;

import com.deswaef.wowscrappie.importingservice.battlenet.wow.auctions.client.domain.RealmItem;
import com.deswaef.wowscrappie.importingservice.battlenet.wow.client.BattlenetConverter;
import com.deswaef.wowscrappie.importingservice.battlenet.wow.auctions.client.domain.AuctionItem;

import java.util.List;

public class AuctionSnapshot extends AbstractBattlenetDto {

    private List<RealmItem> realms;
    private List<AuctionItem> auctions;

    public List<RealmItem> realms() {
        return realms;
    }

    public AuctionSnapshot realms(List<RealmItem> realms) {
        this.realms = realms;
        return this;
    }

    public List<AuctionItem> auctions() {
        return auctions;
    }

    public AuctionSnapshot auctions(List<AuctionItem> auctions) {
        this.auctions = auctions;
        return this;
    }

    public static AuctionSnapshot fromJson(String json) {
        if (isErrorMessage(json)) {
            throwException(json);
            return null;
        } else {
            return BattlenetConverter.convert(json, AuctionSnapshot.class);
        }
    }

}