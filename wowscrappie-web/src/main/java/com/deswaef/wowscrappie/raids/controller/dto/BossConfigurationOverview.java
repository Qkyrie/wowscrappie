package com.deswaef.wowscrappie.raids.controller.dto;

public class BossConfigurationOverview {
    private long id;
    private long macroCount;
    private long waCount;
    private long tmwCount;

    public long getMacroCount() {
        return macroCount;
    }

    public BossConfigurationOverview setMacroCount(long macroCount) {
        this.macroCount = macroCount;
        return this;
    }

    public long getWaCount() {
        return waCount;
    }

    public BossConfigurationOverview setWaCount(long waCount) {
        this.waCount = waCount;
        return this;
    }

    public long getTmwCount() {
        return tmwCount;
    }

    public BossConfigurationOverview setTmwCount(long tmwCount) {
        this.tmwCount = tmwCount;
        return this;
    }

    public long getId() {
        return id;
    }

    public BossConfigurationOverview setId(long id) {
        this.id = id;
        return this;
    }
}
