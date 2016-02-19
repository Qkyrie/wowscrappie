package com.deswaef.heureka.battlenet.wow.auctions.client.domain;

public class ModifierDto {
    private long type;
    private long value;

    public long type() {
        return type;
    }

    public ModifierDto type(long type) {
        this.type = type;
        return this;
    }

    public long value() {
        return value;
    }

    public ModifierDto value(long value) {
        this.value = value;
        return this;
    }
}
