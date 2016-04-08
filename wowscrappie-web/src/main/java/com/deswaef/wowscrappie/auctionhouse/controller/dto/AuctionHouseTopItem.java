package com.deswaef.wowscrappie.auctionhouse.controller.dto;

public class AuctionHouseTopItem {

    private String name;
    private double totalCount;

    public String getName() {
        return name;
    }

    public AuctionHouseTopItem setName(String name) {
        this.name = name;
        return this;
    }

    public double getTotalCount() {
        return totalCount;
    }

    public AuctionHouseTopItem setTotalCount(double totalCount) {
        this.totalCount = totalCount;
        return this;
    }
}
