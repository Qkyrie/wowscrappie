package com.deswaef.weakauras.contribution.controller.dto;

public class ContributionCommand {

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

    public String getWowClass() {
        return wowClass;
    }

    public void setWowClass(String wowClass) {
        this.wowClass = wowClass;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getActualValue() {
        return actualValue;
    }

    public void setActualValue(String actualValue) {
        this.actualValue = actualValue;
    }

    public String[] getScreenshots() {
        return screenshots;
    }

    public void setScreenshots(String[] screenshots) {
        this.screenshots = screenshots;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getChooseOption() {
        return chooseOption;
    }

    public void setChooseOption(String chooseOption) {
        this.chooseOption = chooseOption;
    }

    public long getRaidId() {
        return raidId;
    }

    public ContributionCommand setRaidId(long raidId) {
        this.raidId = raidId;
        return this;
    }

    public long getBossId() {
        return bossId;
    }

    public ContributionCommand setBossId(long bossId) {
        this.bossId = bossId;
        return this;
    }
}
