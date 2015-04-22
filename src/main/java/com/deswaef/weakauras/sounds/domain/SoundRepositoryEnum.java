package com.deswaef.weakauras.sounds.domain;

public enum SoundRepositoryEnum {
    NONE("no sounds"), DEFAULT("default sound"), SURPRISE("omg surprise me!");

    private String explanation;

    SoundRepositoryEnum(String explanation) {
        this.explanation = explanation;
    }

    public String getExplanation() {
        return explanation;
    }
}
