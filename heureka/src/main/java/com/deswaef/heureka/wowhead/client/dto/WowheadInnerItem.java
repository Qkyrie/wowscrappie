package com.deswaef.heureka.wowhead.client.dto;

import javax.xml.bind.annotation.XmlElement;

public class WowheadInnerItem {

    @XmlElement(name = "level")
    private int level;

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "quality")
    private String quality;

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public String getQuality() {
        return quality;
    }
}
