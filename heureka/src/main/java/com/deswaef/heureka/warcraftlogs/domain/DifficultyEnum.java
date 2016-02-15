package com.deswaef.heureka.warcraftlogs.domain;

import java.util.Arrays;

public enum DifficultyEnum {
    NORMAL(3, "N"), HEROIC(4, "H"), UNKNOWN(0, "U");

    private int id;
    private String name;

    DifficultyEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static DifficultyEnum fromId(int id) {
        return Arrays.asList(values())
                .stream()
                .filter(x -> x.getId() == id)
                .findAny().orElse(UNKNOWN);
    }
}
