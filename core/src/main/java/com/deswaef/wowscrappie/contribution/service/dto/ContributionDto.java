package com.deswaef.wowscrappie.contribution.service.dto;

public class ContributionDto {

    public static final String OPTION_CLASSSPEC = "classspec";
    public static final String OPTION_BOSSFIGHT = "bossfight";
    public static final String CATEGORY_TMW = "TMW";
    public static final String CATEGORY_WA = "WA";
    public static final String CATEGORY_MACRO = "MACRO";

    private String wowClass;
    private String spec;

    private long raidId;
    private long bossId;

    private String caption;
    private String comments;
    private String category;
    private String chooseOption;
    private String actualValue;
    private String[] screenshots;

    public String wowClass() {
        return wowClass;
    }

    public ContributionDto wowClass(String wowClass) {
        this.wowClass = wowClass;
        return this;
    }

    public String spec() {
        return spec;
    }

    public ContributionDto spec(String spec) {
        this.spec = spec;
        return this;
    }

    public long raidId() {
        return raidId;
    }

    public ContributionDto raidId(long raidId) {
        this.raidId = raidId;
        return this;
    }

    public long bossId() {
        return bossId;
    }

    public ContributionDto bossId(long bossId) {
        this.bossId = bossId;
        return this;
    }

    public String caption() {
        return caption;
    }

    public ContributionDto caption(String caption) {
        this.caption = caption;
        return this;
    }

    public String comments() {
        return comments;
    }

    public ContributionDto comments(String comments) {
        this.comments = comments;
        return this;
    }

    public String category() {
        return category;
    }

    public ContributionDto category(String category) {
        this.category = category;
        return this;
    }

    public String chooseOption() {
        return chooseOption;
    }

    public ContributionDto chooseOption(String chooseOption) {
        this.chooseOption = chooseOption;
        return this;
    }

    public String actualValue() {
        return actualValue;
    }

    public ContributionDto actualValue(String actualValue) {
        this.actualValue = actualValue;
        return this;
    }

    public String[] screenshots() {
        return screenshots;
    }

    public ContributionDto screenshots(String[] screenshots) {
        this.screenshots = screenshots;
        return this;
    }
}
