package com.deswaef.wowscrappie.auctionhouse.controller.dto;

import com.deswaef.wowscrappie.auctionhouse.domain.AuctionHouseSnapshot;
import com.deswaef.wowscrappie.auctionhouse.domain.DailyAuctionSnapshot;
import org.ocpsoft.prettytime.PrettyTime;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AuctionHouseSnapshotDto implements Serializable {

    private static final PrettyTime PRETTY_TIME = new PrettyTime(Locale.ENGLISH);
    private static final DateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy hh:ss");
    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");


    private double minimumBidCoppers;
    private double maximumBidCoppers;
    private double minimumBuyoutCoppers;
    private double maximumBuyoutCoppers;
    private double medianBidCoppers;
    private double medianBuyoutCoppers;
    private double stdevBidCoppers;
    private double stdevBuyoutCoppers;
    private long quantity;
    private double averageBidCoppers;
    private double averageBuyoutCoppers;
    private String exportTimePretty;
    private String actualExportTime;
    private Date exportTime;
    private String itemName;
    private long itemId;
    private String realmName;
    private long realmId;

    public AuctionHouseSnapshotDto() {
    }

    public static AuctionHouseSnapshotDto from(DailyAuctionSnapshot auctionHouseSnapshot) {
        return new AuctionHouseSnapshotDto()
                .setMinimumBidCoppers(auctionHouseSnapshot.getMinimumBid())
                .setMaximumBidCoppers((auctionHouseSnapshot.getMaximumBid()))
                .setMinimumBuyoutCoppers(auctionHouseSnapshot.getMinimumBuyout())
                .setMaximumBuyoutCoppers((auctionHouseSnapshot.getMaximumBuyout()))
                .setAverageBidCoppers((auctionHouseSnapshot.getAverageBid()))
                .setAverageBuyoutCoppers((auctionHouseSnapshot.getAverageBuyout()))
                .setMedianBuyoutCoppers(auctionHouseSnapshot.getMedianBuyout())
                .setMedianBidCoppers(auctionHouseSnapshot.getMedianBid())
                .setStdevBidCoppers(auctionHouseSnapshot.getStdevBid())
                .setStdevBuyoutCoppers(auctionHouseSnapshot.getStdevBuyout())
                .setQuantity(auctionHouseSnapshot.getQuantity())
                .setItemId(auctionHouseSnapshot.getItemId())
                .setRealmId(auctionHouseSnapshot.getRealmId())
                .setExportTime(auctionHouseSnapshot.getExportTime())
                .setExportTimePretty(dateFormat.format(auctionHouseSnapshot.getExportTime()))
                .setActualExportTime(dateFormat.format(auctionHouseSnapshot.getExportTime()));
    }

    public static AuctionHouseSnapshotDto from(AuctionHouseSnapshot auctionHouseSnapshot) {
        return new AuctionHouseSnapshotDto()
                .setMinimumBidCoppers(auctionHouseSnapshot.getMinimumBid())
                .setMaximumBidCoppers((auctionHouseSnapshot.getMaximumBid()))
                .setMinimumBuyoutCoppers(auctionHouseSnapshot.getMinimumBuyout())
                .setMaximumBuyoutCoppers((auctionHouseSnapshot.getMaximumBuyout()))
                .setAverageBidCoppers((auctionHouseSnapshot.getAverageBid()))
                .setAverageBuyoutCoppers((auctionHouseSnapshot.getAverageBuyout()))
                .setMedianBuyoutCoppers(auctionHouseSnapshot.getMedianBuyout())
                .setMedianBidCoppers(auctionHouseSnapshot.getMedianBid())
                .setStdevBidCoppers(auctionHouseSnapshot.getStdevBid())
                .setStdevBuyoutCoppers(auctionHouseSnapshot.getStdevBuyout())
                .setQuantity(auctionHouseSnapshot.getQuantity())
                .setItemId(auctionHouseSnapshot.getItem().getId())
                .setItemName(auctionHouseSnapshot.getItem().getName())
                .setRealmName(auctionHouseSnapshot.getRealm().getName())
                .setRealmId(auctionHouseSnapshot.getRealm().getId())
                .setExportTime(auctionHouseSnapshot.getExportTime())
                .setExportTimePretty(PRETTY_TIME.format(auctionHouseSnapshot.getExportTime()))
                .setActualExportTime(dateTimeFormat.format(auctionHouseSnapshot.getExportTime()));
    }

    public double getMinimumBidCoppers() {
        return minimumBidCoppers;
    }

    public AuctionHouseSnapshotDto setMinimumBidCoppers(double minimumBidCoppers) {
        this.minimumBidCoppers = minimumBidCoppers;
        return this;
    }

    public double getMaximumBidCoppers() {
        return maximumBidCoppers;
    }

    public AuctionHouseSnapshotDto setMaximumBidCoppers(double maximumBidCoppers) {
        this.maximumBidCoppers = maximumBidCoppers;
        return this;
    }

    public double getMinimumBuyoutCoppers() {
        return minimumBuyoutCoppers;
    }

    public AuctionHouseSnapshotDto setMinimumBuyoutCoppers(double minimumBuyoutCoppers) {
        this.minimumBuyoutCoppers = minimumBuyoutCoppers;
        return this;
    }

    public double getMaximumBuyoutCoppers() {
        return maximumBuyoutCoppers;
    }

    public AuctionHouseSnapshotDto setMaximumBuyoutCoppers(double maximumBuyoutCoppers) {
        this.maximumBuyoutCoppers = maximumBuyoutCoppers;
        return this;
    }

    public double getMedianBidCoppers() {
        return medianBidCoppers;
    }

    public AuctionHouseSnapshotDto setMedianBidCoppers(double medianBidCoppers) {
        this.medianBidCoppers = medianBidCoppers;
        return this;
    }

    public double getMedianBuyoutCoppers() {
        return medianBuyoutCoppers;
    }

    public AuctionHouseSnapshotDto setMedianBuyoutCoppers(double medianBuyoutCoppers) {
        this.medianBuyoutCoppers = medianBuyoutCoppers;
        return this;
    }

    public double getStdevBidCoppers() {
        return stdevBidCoppers;
    }

    public AuctionHouseSnapshotDto setStdevBidCoppers(double stdevBidCoppers) {
        this.stdevBidCoppers = stdevBidCoppers;
        return this;
    }

    public double getStdevBuyoutCoppers() {
        return stdevBuyoutCoppers;
    }

    public AuctionHouseSnapshotDto setStdevBuyoutCoppers(double stdevBuyoutCoppers) {
        this.stdevBuyoutCoppers = stdevBuyoutCoppers;
        return this;
    }

    public long getQuantity() {
        return quantity;
    }

    public AuctionHouseSnapshotDto setQuantity(long quantity) {
        this.quantity = quantity;
        return this;
    }

    public double getAverageBidCoppers() {
        return averageBidCoppers;
    }

    public AuctionHouseSnapshotDto setAverageBidCoppers(double averageBidCoppers) {
        this.averageBidCoppers = averageBidCoppers;
        return this;
    }

    public double getAverageBuyoutCoppers() {
        return averageBuyoutCoppers;
    }

    public AuctionHouseSnapshotDto setAverageBuyoutCoppers(double averageBuyoutCoppers) {
        this.averageBuyoutCoppers = averageBuyoutCoppers;
        return this;
    }

    public String getExportTimePretty() {
        return exportTimePretty;
    }

    public AuctionHouseSnapshotDto setExportTimePretty(String exportTimePretty) {
        this.exportTimePretty = exportTimePretty;
        return this;
    }

    public String getActualExportTime() {
        return actualExportTime;
    }

    public AuctionHouseSnapshotDto setActualExportTime(String actualExportTime) {
        this.actualExportTime = actualExportTime;
        return this;
    }

    public Date getExportTime() {
        return exportTime;
    }

    public AuctionHouseSnapshotDto setExportTime(Date exportTime) {
        this.exportTime = exportTime;
        return this;
    }

    public String getItemName() {
        return itemName;
    }

    public AuctionHouseSnapshotDto setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public long getItemId() {
        return itemId;
    }

    public AuctionHouseSnapshotDto setItemId(long itemId) {
        this.itemId = itemId;
        return this;
    }

    public String getRealmName() {
        return realmName;
    }

    public AuctionHouseSnapshotDto setRealmName(String realmName) {
        this.realmName = realmName;
        return this;
    }

    public long getRealmId() {
        return realmId;
    }

    public AuctionHouseSnapshotDto setRealmId(long realmId) {
        this.realmId = realmId;
        return this;
    }
}
