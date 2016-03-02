package com.deswaef.wowscrappie.auctionhouse.domain;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

public class AuctionItemBonusList {

    @Field(type = FieldType.Integer)
    private int bonusListId;

    public int getBonusListId() {
        return bonusListId;
    }

    public AuctionItemBonusList setBonusListId(int bonusListId) {
        this.bonusListId = bonusListId;
        return this;
    }
}
