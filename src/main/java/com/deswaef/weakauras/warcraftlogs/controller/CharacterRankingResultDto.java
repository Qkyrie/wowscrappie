package com.deswaef.weakauras.warcraftlogs.controller;

public class CharacterRankingResultDto {

    private String encounterName;
    private String classAndSpec;
    private String guild;
    private Long rank;
    private Long outOf;
    private Long duration;
    private String durationPretty;
    private Long startTime;
    private String rankPercentage;
    private String startTimePretty;
    private String reportID;
    private Long fightID;
    private Integer difficulty;
    private String difficultyName;
    private Integer size;
    private Integer itemLevel;
    private Double total;

    public String getEncounterName() {
        return encounterName;
    }

    public CharacterRankingResultDto setEncounterName(String encounterName) {
        this.encounterName = encounterName;
        return this;
    }

    public String getClassAndSpec() {
        return classAndSpec;
    }

    public CharacterRankingResultDto setClassAndSpec(String classAndSpec) {
        this.classAndSpec = classAndSpec;
        return this;
    }

    public String getGuild() {
        return guild;
    }

    public CharacterRankingResultDto setGuild(String guild) {
        this.guild = guild;
        return this;
    }

    public Long getRank() {
        return rank;
    }

    public CharacterRankingResultDto setRank(Long rank) {
        this.rank = rank;
        return this;
    }

    public Long getOutOf() {
        return outOf;
    }

    public CharacterRankingResultDto setOutOf(Long outOf) {
        this.outOf = outOf;
        return this;
    }

    public Long getDuration() {
        return duration;
    }

    public CharacterRankingResultDto setDuration(Long duration) {
        this.duration = duration;
        return this;
    }

    public Long getStartTime() {
        return startTime;
    }

    public CharacterRankingResultDto setStartTime(Long startTime) {
        this.startTime = startTime;
        return this;
    }

    public String getStartTimePretty() {
        return startTimePretty;
    }

    public CharacterRankingResultDto setStartTimePretty(String startTimePretty) {
        this.startTimePretty = startTimePretty;
        return this;
    }

    public String getReportID() {
        return reportID;
    }

    public CharacterRankingResultDto setReportID(String reportID) {
        this.reportID = reportID;
        return this;
    }

    public Long getFightID() {
        return fightID;
    }

    public CharacterRankingResultDto setFightID(Long fightID) {
        this.fightID = fightID;
        return this;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public CharacterRankingResultDto setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public String getDifficultyName() {
        return difficultyName;
    }

    public CharacterRankingResultDto setDifficultyName(String difficultyName) {
        this.difficultyName = difficultyName;
        return this;
    }

    public Integer getSize() {
        return size;
    }

    public CharacterRankingResultDto setSize(Integer size) {
        this.size = size;
        return this;
    }

    public Integer getItemLevel() {
        return itemLevel;
    }

    public CharacterRankingResultDto setItemLevel(Integer itemLevel) {
        this.itemLevel = itemLevel;
        return this;
    }

    public Double getTotal() {
        return total;
    }

    public CharacterRankingResultDto setTotal(Double total) {
        this.total = total;
        return this;
    }


    public String getDurationPretty() {
        return durationPretty;
    }

    public CharacterRankingResultDto setDurationPretty(String durationPretty) {
        this.durationPretty = durationPretty;
        return this;
    }

    public String getRankPercentage() {
        return rankPercentage;
    }

    public CharacterRankingResultDto setRankPercentage(String rankPercentage) {
        this.rankPercentage = rankPercentage;
        return this;
    }
}
