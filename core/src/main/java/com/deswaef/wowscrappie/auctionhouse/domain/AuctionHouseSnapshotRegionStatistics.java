package com.deswaef.wowscrappie.auctionhouse.domain;

import com.deswaef.wowscrappie.item.domain.Item;
import com.deswaef.wowscrappie.realm.domain.Locality;

public class AuctionHouseSnapshotRegionStatistics {
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

    public AuctionHouseSnapshotRegionStatistics item(Item item) {
        this.item = item;
        return this;
    }

    public long totalQuantity() {
        return totalQuantity;
    }

    public AuctionHouseSnapshotRegionStatistics totalQuantity(long totalQuantity) {
        this.totalQuantity = totalQuantity;
        return this;
    }

    public long averageQuantityPerServer() {
        return averageQuantityPerServer;
    }

    public AuctionHouseSnapshotRegionStatistics averageQuantityPerServer(long averageQuantityPerServer) {
        this.averageQuantityPerServer = averageQuantityPerServer;
        return this;
    }

    public long medianQuantityPerServer() {
        return medianQuantityPerServer;
    }

    public AuctionHouseSnapshotRegionStatistics medianQuantityPerServer(long meanQuantityPerServer) {
        this.medianQuantityPerServer = meanQuantityPerServer;
        return this;
    }

    public double medianBid() {
        return medianBid;
    }

    public AuctionHouseSnapshotRegionStatistics medianBid(double medianBid) {
        this.medianBid = medianBid;
        return this;
    }

    public double medianBuyout() {
        return medianBuyout;
    }

    public AuctionHouseSnapshotRegionStatistics medianBuyout(double medianBuyout) {
        this.medianBuyout = medianBuyout;
        return this;
    }

    public double averageBid() {
        return averageBid;
    }

    public AuctionHouseSnapshotRegionStatistics averageBid(double averageBid) {
        this.averageBid = averageBid;
        return this;
    }

    public double averageBuyout() {
        return averageBuyout;
    }

    public AuctionHouseSnapshotRegionStatistics averageBuyout(double averageBuyout) {
        this.averageBuyout = averageBuyout;
        return this;
    }

    public Locality locality() {
        return locality;
    }

    public AuctionHouseSnapshotRegionStatistics locality(Locality locality) {
        this.locality = locality;
        return this;
    }
}
