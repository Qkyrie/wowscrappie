package com.deswaef.wowscrappie.battlenet.api.domain;

public enum CharacterProfileFields {
    ACHIEVEMENTS("achievements"), APPEARANCE("appearance"), FEED("feed"), GUILD("guild"),
    HUNTER_PETS("hunterPets"), ITEMS("items"), MOUNTS("mounts"), PETS("pets"), PET_SLOTS("petSlots"),
    PROGRESSION("progression"), PVP("pvp"), QUESTS("quests"), REPUTATION("reputation"), STATS("stats"), TALENTS("talents"),
    TITLES("titles"), AUDIT("audit");

    private String name;

    CharacterProfileFields(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
