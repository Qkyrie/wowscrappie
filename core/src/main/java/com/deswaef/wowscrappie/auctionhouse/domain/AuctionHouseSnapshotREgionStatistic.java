package com.deswaef.wowscrappie.auctionhouse.domain;

import com.deswaef.wowscrappie.item.domain.Item;
import com.deswaef.wowscrappie.realm.domain.Locality;

public class AuctionHouseSnapshotRegionStatistic {
    private Item item;
    private Locality locality;
    private long totalQuantity;
    private long averageQuantityPerServer;
    private long medianQuantityPerServer;
    private double medianBid;
    private double medianBuyout;
    private double averageBid;
    private double averageBuyout;

    public Item item() {
        return item;
    }

    public AuctionHouseSnapshotRegionStatistic item(Item item) {
        this.item = item;
        return this;
    }

    public long totalQuantity() {
        return totalQuantity;
    }

    public AuctionHouseSnapshotRegionStatistic totalQuantity(long totalQuantity) {
        this.totalQuantity = totalQuantity;
        return this;
    }

    public long averageQuantityPerServer() {
        return averageQuantityPerServer;
    }

    public AuctionHouseSnapshotRegionStatistic averageQuantityPerServer(long averageQuantityPerServer) {
        this.averageQuantityPerServer = averageQuantityPerServer;
        return this;
    }

    public long medianQuantityPerServer() {
        return medianQuantityPerServer;
    }

    public AuctionHouseSnapshotRegionStatistic medianQuantityPerServer(long meanQuantityPerServer) {
        this.medianQuantityPerServer = meanQuantityPerServer;
        return this;
    }

    public double medianBid() {
        return medianBid;
    }

    public AuctionHouseSnapshotRegionStatistic medianBid(double medianBid) {
        this.medianBid = medianBid;
        return this;
    }

    public double medianBuyout() {
        return medianBuyout;
    }

    public AuctionHouseSnapshotRegionStatistic medianBuyout(double medianBuyout) {
        this.medianBuyout = medianBuyout;
        return this;
    }

    public double averageBid() {
        return averageBid;
    }

    public AuctionHouseSnapshotRegionStatistic averageBid(double averageBid) {
        this.averageBid = averageBid;
        return this;
    }

    public double averageBuyout() {
        return averageBuyout;
    }

    public AuctionHouseSnapshotRegionStatistic averageBuyout(double averageBuyout) {
        this.averageBuyout = averageBuyout;
        return this;
    }

    public Locality locality() {
        return locality;
    }

    public AuctionHouseSnapshotRegionStatistic locality(Locality locality) {
        this.locality = locality;
        return this;
    }
}
