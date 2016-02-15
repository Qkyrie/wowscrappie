package com.deswaef.heureka.battlenet.wow.domain;

import com.deswaef.heureka.battlenet.wow.client.BattlenetConverter;
import com.deswaef.heureka.battlenet.wow.auctions.client.domain.AuctionItem;

import java.util.List;

public class AuctionSnapshot extends AbstractBattlenetDto {

    private List<AuctionItem> auctions;

    public List<AuctionItem> getAuctions() {
        return auctions;
    }

    public void setAuctions(List<AuctionItem> auctions) {
        this.auctions = auctions;
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