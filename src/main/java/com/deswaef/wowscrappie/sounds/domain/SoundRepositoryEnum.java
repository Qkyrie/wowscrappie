package com.deswaef.wowscrappie.sounds.domain;

public enum SoundRepositoryEnum {
    NONE("no sounds", null), DEFAULT("default sound", "alerts"), SURPRISE("omg surprise me!", "fun");

    private String explanation;
    private String storeValue;

    SoundRepositoryEnum(String explanation, String storeValue) {
        this.explanation = explanation;
        this.storeValue = storeValue;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getStoreValue() {
        return storeValue;
    }
}
