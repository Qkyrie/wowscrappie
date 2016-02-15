package com.deswaef.heureka.warcraftlogs.domain.rankings;

import com.google.gson.annotations.SerializedName;

public class CharacterRanking {
    private Long encounter;
    @SerializedName("class")
    private Long classId;
    private Long spec;
    private String guild;
    private Long rank;
    private Long outOf;
    private Long duration;
    private Long startTime;
    private String reportID;
    private Long fightID;
    private Integer difficulty;
    private Integer size;
    private Integer itemLevel;
    private Double total;

    public Long getEncounter() {
        return encounter;
    }

    public CharacterRanking setEncounter(Long encounter) {
        this.encounter = encounter;
        return this;
    }

    public Long getClassId() {
        return classId;
    }

    public CharacterRanking setClassId(Long classId) {
        this.classId = classId;
        return this;
    }

    public Long getSpec() {
        return spec;
    }

    public CharacterRanking setSpec(Long spec) {
        this.spec = spec;
        return this;
    }

    public String getGuild() {
        return guild;
    }

    public CharacterRanking setGuild(String guild) {
        this.guild = guild;
        return this;
    }

    public Long getRank() {
        return rank;
    }

    public CharacterRanking setRank(Long rank) {
        this.rank = rank;
        return this;
    }

    public Long getOutOf() {
        return outOf;
    }

    public CharacterRanking setOutOf(Long outOf) {
        this.outOf = outOf;
        return this;
    }

    public Long getDuration() {
        return duration;
    }

    public CharacterRanking setDuration(Long duration) {
        this.duration = duration;
        return this;
    }

    public Long getStartTime() {
        return startTime;
    }

    public CharacterRanking setStartTime(Long startTime) {
        this.startTime = startTime;
        return this;
    }

    public String getReportID() {
        return reportID;
    }

    public CharacterRanking setReportID(String reportID) {
        this.reportID = reportID;
        return this;
    }

    public Long getFightID() {
        return fightID;
    }

    public CharacterRanking setFightID(Long fightID) {
        this.fightID = fightID;
        return this;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public CharacterRanking setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public Integer getSize() {
        return size;
    }

    public CharacterRanking setSize(Integer size) {
        this.size = size;
        return this;
    }

    public Integer getItemLevel() {
        return itemLevel;
    }

    public CharacterRanking setItemLevel(Integer itemLevel) {
        this.itemLevel = itemLevel;
        return this;
    }

    public Double getTotal() {
        return total;
    }

    public CharacterRanking setTotal(Double total) {
        this.total = total;
        return this;
    }
}
