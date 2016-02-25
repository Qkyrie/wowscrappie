package com.deswaef.wowscrappie.auctionhouse.controller.dto;

import com.deswaef.wowscrappie.auctionhouse.domain.AuctionHouseSnapshotRegionStatistics;
import com.deswaef.wowscrappie.item.domain.Item;
import com.deswaef.wowscrappie.realm.domain.Locality;

import java.io.Serializable;

public class AuctionHouseRegionStatisticDto implements Serializable {

    private Item item;
    private Locality locality;
    private long totalQuantity;
    private long averageQuantityPerServer;
    private long medianQuantityPerServer;
    private double medianBid;
    private double medianBuyout;
    private double averageBid;
    private double averageBuyout;

    public static AuctionHouseRegionStatisticDto from(AuctionHouseSnapshotRegionStatistics stat) {
        return new AuctionHouseRegionStatisticDto()
                .setItem(stat.item())
                .setLocality(stat.locality())
                .setTotalQuantity(stat.totalQuantity())
                .setAverageQuantityPerServer(stat.averageQuantityPerServer())
                .setMedianQuantityPerServer(stat.medianQuantityPerServer())
                .setMedianBid(stat.medianBid())
                .setMedianBuyout(stat.medianBuyout())
                .setAverageBid(stat.averageBid())
                .setAverageBuyout(stat.averageBuyout());
    }

    public Locality getLocality() {
        return locality;
    }

    public AuctionHouseRegionStatisticDto setLocality(Locality locality) {
        this.locality = locality;
        return this;
    }

    public Item getItem() {
        return item;
    }

    public AuctionHouseRegionStatisticDto setItem(Item item) {
        this.item = item;
        return this;
    }

    public long getTotalQuantity() {
        return totalQuantity;
    }

    public AuctionHouseRegionStatisticDto setTotalQuantity(long totalQuantity) {
        this.totalQuantity = totalQuantity;
        return this;
    }

    public long getAverageQuantityPerServer() {
        return averageQuantityPerServer;
    }

    public AuctionHouseRegionStatisticDto setAverageQuantityPerServer(long averageQuantityPerServer) {
        this.averageQuantityPerServer = averageQuantityPerServer;
        return this;
    }

    public long getMedianQuantityPerServer() {
        return medianQuantityPerServer;
    }

    public AuctionHouseRegionStatisticDto setMedianQuantityPerServer(long medianQuantityPerServer) {
        this.medianQuantityPerServer = medianQuantityPerServer;
        return this;
    }

    public double getMedianBid() {
        return medianBid;
    }

    public AuctionHouseRegionStatisticDto setMedianBid(double medianBid) {
        this.medianBid = medianBid;
        return this;
    }

    public double getMedianBuyout() {
        return medianBuyout;
    }

    public AuctionHouseRegionStatisticDto setMedianBuyout(double medianBuyout) {
        this.medianBuyout = medianBuyout;
        return this;
    }

    public double getAverageBid() {
        return averageBid;
    }

    public AuctionHouseRegionStatisticDto setAverageBid(double averageBid) {
        this.averageBid = averageBid;
        return this;
    }

    public double getAverageBuyout() {
        return averageBuyout;
    }

    public AuctionHouseRegionStatisticDto setAverageBuyout(double averageBuyout) {
        this.averageBuyout = averageBuyout;
        return this;
    }
}
