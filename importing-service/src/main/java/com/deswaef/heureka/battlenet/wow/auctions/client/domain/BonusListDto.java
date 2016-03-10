package com.deswaef.heureka.battlenet.wow.auctions.client.domain;

public class BonusListDto {
    private int bonusListId;

    public int bonusListId() {
        return bonusListId;
    }

    public BonusListDto bonusListId(int bonusListId) {
        this.bonusListId = bonusListId;
        return this;
    }
}
