package com.deswaef.heureka.warcraftlogs.domain.rankings;

import com.google.gson.annotations.SerializedName;

public class Ranking {
    private String name;
    @SerializedName("class")
    private Long className;
    private Long spec;
    private Double total;
    private Long duration;
    private Long startTime;
    private Long fightID;
    private String reportID;
    private String guild;
    private String server;
    private Integer itemLevel;
    private Integer size;

    public String getName() {
        return name;
    }

    public Ranking setName(String name) {
        this.name = name;
        return this;
    }

    public Long getClassName() {
        return className;
    }

    public Ranking setClassName(Long className) {
        this.className = className;
        return this;
    }

    public Long getSpec() {
        return spec;
    }

    public Ranking setSpec(Long spec) {
        this.spec = spec;
        return this;
    }

    public Double getTotal() {
        return total;
    }

    public Ranking setTotal(Double total) {
        this.total = total;
        return this;
    }

    public Long getDuration() {
        return duration;
    }

    public Ranking setDuration(Long duration) {
        this.duration = duration;
        return this;
    }

    public Long getStartTime() {
        return startTime;
    }

    public Ranking setStartTime(Long startTime) {
        this.startTime = startTime;
        return this;
    }

    public Long getFightID() {
        return fightID;
    }

    public Ranking setFightID(Long fightID) {
        this.fightID = fightID;
        return this;
    }

    public String getReportID() {
        return reportID;
    }

    public Ranking setReportID(String reportID) {
        this.reportID = reportID;
        return this;
    }

    public String getGuild() {
        return guild;
    }

    public Ranking setGuild(String guild) {
        this.guild = guild;
        return this;
    }

    public String getServer() {
        return server;
    }

    public Ranking setServer(String server) {
        this.server = server;
        return this;
    }

    public Integer getItemLevel() {
        return itemLevel;
    }

    public Ranking setItemLevel(Integer itemLevel) {
        this.itemLevel = itemLevel;
        return this;
    }

    public Integer getSize() {
        return size;
    }

    public Ranking setSize(Integer size) {
        this.size = size;
        return this;
    }
}
