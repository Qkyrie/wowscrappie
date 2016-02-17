package com.deswaef.wowscrappie.auctionhouse.controller.dto;

import com.deswaef.wowscrappie.auctionhouse.domain.AuctionHouseSnapshot;
import org.ocpsoft.prettytime.PrettyTime;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.deswaef.wowscrappie.auctionhouse.controller.dto.GoldDto.fromDouble;

public class AuctionHouseSnapshotDto implements Serializable {

    private static final PrettyTime PRETTY_TIME = new PrettyTime(Locale.ENGLISH);
    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:ss");


    private GoldDto minimumBidCoppers;
    private GoldDto maximumBidCoppers;
    private GoldDto minimumBuyoutCoppers;
    private GoldDto maximumBuyoutCoppers;
    private long quantity;
    private GoldDto averageBidCoppers;
    private GoldDto averageBuyoutCoppers;
    private String exportTimePretty;
    private String actualExportTime;
    private Date exportTime;
    private String itemName;
    private long itemId;
    private String realmName;
    private long realmId;

    public AuctionHouseSnapshotDto() {
    }

    public static AuctionHouseSnapshotDto from(AuctionHouseSnapshot auctionHouseSnapshot) {
        return new AuctionHouseSnapshotDto()
                .setMinimumBidCoppers(fromDouble(auctionHouseSnapshot.getMinimumBid()))
                .setMaximumBidCoppers(fromDouble(auctionHouseSnapshot.getMaximumBid()))
                .setMinimumBuyoutCoppers(fromDouble(auctionHouseSnapshot.getMinimumBuyout()))
                .setMaximumBuyoutCoppers(fromDouble(auctionHouseSnapshot.getMaximumBuyout()))
                .setAverageBidCoppers(fromDouble(auctionHouseSnapshot.getAverageBid()))
                .setAverageBuyoutCoppers(fromDouble(auctionHouseSnapshot.getAverageBuyout()))
                .setQuantity(auctionHouseSnapshot.getQuantity())
                .setItemId(auctionHouseSnapshot.getItem().getId())
                .setItemName(auctionHouseSnapshot.getItem().getName())
                .setRealmName(auctionHouseSnapshot.getRealm().getName())
                .setRealmId(auctionHouseSnapshot.getRealm().getId())
                .setExportTime(auctionHouseSnapshot.getExportTime())
                .setExportTimePretty(PRETTY_TIME.format(auctionHouseSnapshot.getExportTime()))
                .setActualExportTime(dateFormat.format(auctionHouseSnapshot.getExportTime()));
    }

    public GoldDto getMinimumBidCoppers() {
        return minimumBidCoppers;
    }

    public AuctionHouseSnapshotDto setMinimumBidCoppers(GoldDto minimumBidCoppers) {
        this.minimumBidCoppers = minimumBidCoppers;
        return this;
    }

    public GoldDto setMaximumBidCoppers() {
        return maximumBidCoppers;
    }

    public AuctionHouseSnapshotDto setMaximumBidCoppers(GoldDto maximumBidCoppers) {
        this.maximumBidCoppers = maximumBidCoppers;
        return this;
    }

    public GoldDto getMinimumBuyoutCoppers() {
        return minimumBuyoutCoppers;
    }

    public AuctionHouseSnapshotDto setMinimumBuyoutCoppers(GoldDto minimumBuyoutCoppers) {
        this.minimumBuyoutCoppers = minimumBuyoutCoppers;
        return this;
    }

    public GoldDto getMaximumBuyoutCoppers() {
        return maximumBuyoutCoppers;
    }

    public AuctionHouseSnapshotDto setMaximumBuyoutCoppers(GoldDto maximumBuyoutCoppers) {
        this.maximumBuyoutCoppers = maximumBuyoutCoppers;
        return this;
    }

    public long getQuantity() {
        return quantity;
    }

    public AuctionHouseSnapshotDto setQuantity(long quantity) {
        this.quantity = quantity;
        return this;
    }

    public GoldDto getAverageBidCoppers() {
        return averageBidCoppers;
    }

    public AuctionHouseSnapshotDto setAverageBidCoppers(GoldDto averageBidCoppers) {
        this.averageBidCoppers = averageBidCoppers;
        return this;
    }

    public GoldDto getAverageBuyoutCoppers() {
        return averageBuyoutCoppers;
    }

    public AuctionHouseSnapshotDto setAverageBuyoutCoppers(GoldDto averageBuyoutCoppers) {
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
