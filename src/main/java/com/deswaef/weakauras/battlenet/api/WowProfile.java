package com.deswaef.weakauras.battlenet.api;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class WowProfile implements Serializable {

    private Character[] characters;

    public Character[] getCharacters() {
        return characters;
    }

    public WowProfile setCharacters(Character[] characters) {
        this.characters = characters;
        return this;
    }

    protected static class Character {

        public Character() {
        }

        private String name;
        private String realm;
        private String battlegroup;
        @JsonProperty("class")
        private int classId;
        private int gender;
        private int race;
        private int level;
        private long achievementPoints;
        private String thumbnail;
        private String guild;
        private String guildRealm;
        private Spec spec;

        public int getRace() {
            return race;
        }

        public Character setRace(int race) {
            this.race = race;
            return this;
        }

        public String getName() {
            return name;
        }

        public Character setName(String name) {
            this.name = name;
            return this;
        }

        public String getRealm() {
            return realm;
        }

        public Character setRealm(String realm) {
            this.realm = realm;
            return this;
        }

        public String getBattlegroup() {
            return battlegroup;
        }

        public Character setBattlegroup(String battlegroup) {
            this.battlegroup = battlegroup;
            return this;
        }

        public int getClassId() {
            return classId;
        }

        public Character setClassId(int classId) {
            this.classId = classId;
            return this;
        }

        public int getGender() {
            return gender;
        }

        public Character setGender(int gender) {
            this.gender = gender;
            return this;
        }

        public int getLevel() {
            return level;
        }

        public Character setLevel(int level) {
            this.level = level;
            return this;
        }

        public long getAchievementPoints() {
            return achievementPoints;
        }

        public Character setAchievementPoints(long achievementPoints) {
            this.achievementPoints = achievementPoints;
            return this;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public Character setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }

        public String getGuild() {
            return guild;
        }

        public Character setGuild(String guild) {
            this.guild = guild;
            return this;
        }

        public String getGuildRealm() {
            return guildRealm;
        }

        public Character setGuildRealm(String guildRealm) {
            this.guildRealm = guildRealm;
            return this;
        }

        public Spec getSpec() {
            return spec;
        }

        public Character setSpec(Spec spec) {
            this.spec = spec;
            return this;
        }
    }

    protected static class Spec {
        public Spec() {
        }

        private String name;
        private String role;
        private String backgroundImage;
        private String icon;
        private String description;
        private int order;

        public String getName() {
            return name;
        }

        public Spec setName(String name) {
            this.name = name;
            return this;
        }

        public String getRole() {
            return role;
        }

        public Spec setRole(String role) {
            this.role = role;
            return this;
        }

        public String getBackgroundImage() {
            return backgroundImage;
        }

        public Spec setBackgroundImage(String backgroundImage) {
            this.backgroundImage = backgroundImage;
            return this;
        }

        public String getIcon() {
            return icon;
        }

        public Spec setIcon(String icon) {
            this.icon = icon;
            return this;
        }

        public String getDescription() {
            return description;
        }

        public Spec setDescription(String description) {
            this.description = description;
            return this;
        }

        public int getOrder() {
            return order;
        }

        public Spec setOrder(int order) {
            this.order = order;
            return this;
        }
    }

}
