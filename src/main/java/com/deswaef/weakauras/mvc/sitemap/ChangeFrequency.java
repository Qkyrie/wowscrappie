package com.deswaef.weakauras.mvc.sitemap;

public enum ChangeFrequency {
    DAILY("daily"), HOURLY("hourly"), WEEKLY("weekly");

    private String name;

    ChangeFrequency(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
