package com.deswaef.wowscrappie.importingservice.warcraftlogs.domain.zones;

public class Bracket {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public Bracket setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Bracket setName(String name) {
        this.name = name;
        return this;
    }
}
