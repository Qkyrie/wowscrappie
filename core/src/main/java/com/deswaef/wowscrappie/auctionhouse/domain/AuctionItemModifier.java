package com.deswaef.wowscrappie.auctionhouse.domain;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

public class AuctionItemModifier {
    @Field(type = FieldType.Integer)
    private int type;
    @Field(type = FieldType.Integer)
    private int value;

    public int getType() {
        return type;
    }

    public AuctionItemModifier setType(int type) {
        this.type = type;
        return this;
    }

    public int getValue() {
        return value;
    }

    public AuctionItemModifier setValue(int value) {
        this.value = value;
        return this;
    }
}
