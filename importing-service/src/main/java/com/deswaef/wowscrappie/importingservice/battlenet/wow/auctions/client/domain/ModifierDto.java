package com.deswaef.wowscrappie.importingservice.battlenet.wow.auctions.client.domain;

public class ModifierDto {
    private int type;
    private int value;

    public int type() {
        return type;
    }

    public ModifierDto type(int type) {
        this.type = type;
        return this;
    }

    public int value() {
        return value;
    }

    public ModifierDto value(int value) {
        this.value = value;
        return this;
    }
}
