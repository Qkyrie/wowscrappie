package com.deswaef.weakauras.warcraftlogs.domain.zones;

public class Encounter {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public Encounter setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Encounter setName(String name) {
        this.name = name;
        return this;
    }
}
