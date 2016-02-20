package com.deswaef.wowscrappie.auctionhouse.controller.dto;

import java.io.Serializable;

public class GoldDto implements Serializable {
    private long fullCoppers;
    private int coppers;
    private int silver;
    private long gold;
    private String printed;

    public GoldDto() {
    }

    public static GoldDto fromDouble(double amount) {
        if (amount == 0) {
            return new GoldDto()
                    .setPrinted("0g 0s 0c");
        } else {

            int coppers = (int) (amount % 100);
            long goldAndSilvers = (long) (amount / 100);
            int silvers = (int) (goldAndSilvers % 100);
            long gold = goldAndSilvers / 100;
            return new GoldDto()
                    .setFullCoppers((long) amount)
                    .setCoppers(coppers)
                    .setSilver(silvers)
                    .setGold(gold)
                    .setPrinted(String.format("%dg %ds %dc", gold, silvers, coppers));
        }
    }


    public long getFullCoppers() {
        return fullCoppers;
    }

    public GoldDto setFullCoppers(long fullCoppers) {
        this.fullCoppers = fullCoppers;
        return this;
    }

    public int getCoppers() {
        return coppers;
    }

    public GoldDto setCoppers(int coppers) {
        this.coppers = coppers;
        return this;
    }

    public int getSilver() {
        return silver;
    }

    public GoldDto setSilver(int silver) {
        this.silver = silver;
        return this;
    }

    public long getGold() {
        return gold;
    }

    public GoldDto setGold(long gold) {
        this.gold = gold;
        return this;
    }

    public String getPrinted() {
        return printed;
    }

    public GoldDto setPrinted(String printed) {
        this.printed = printed;
        return this;
    }

    public String toString() {
        return printed;
    }
}
