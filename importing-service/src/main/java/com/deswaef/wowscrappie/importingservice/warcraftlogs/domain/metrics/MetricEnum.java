package com.deswaef.wowscrappie.importingservice.warcraftlogs.domain.metrics;

public enum MetricEnum {
    DPS("dps"), HPS("hps"), TANKHPS("tankhps"), KRSI("krsi");

    private String name;

    MetricEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
