package com.deswaef.heureka.battlenet.wow.auctions.client.domain;

public class BonusListDto {
    private long bonusListId;

    public long bonusListId() {
        return bonusListId;
    }

    public BonusListDto bonusListId(long bonusListId) {
        this.bonusListId = bonusListId;
        return this;
    }
}
