package com.deswaef.heureka.warcraftlogs.domain.zones;

import java.util.List;

public class Zone {
    private Long id;
    private String name;
    private boolean frozen;
    private List<Encounter> encounters;
    private List<Bracket> brackets;

    public Long getId() {
        return id;
    }

    public Zone setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Zone setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public Zone setFrozen(boolean frozen) {
        this.frozen = frozen;
        return this;
    }

    public List<Encounter> getEncounters() {
        return encounters;
    }

    public Zone setEncounters(List<Encounter> encounters) {
        this.encounters = encounters;
        return this;
    }

    public List<Bracket> getBrackets() {
        return brackets;
    }

    public Zone setBrackets(List<Bracket> brackets) {
        this.brackets = brackets;
        return this;
    }
}
