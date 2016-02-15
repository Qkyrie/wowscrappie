package com.deswaef.wowscrappie.auctionhouse.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Document(indexName = "historic_auction_snapshot", type = "historic_auction_snapshot", shards = 1, replicas = 0)
public class HistoricAuctionHouseSnapshot {

    private static DateFormat format = new SimpleDateFormat("dd.MM.yyyy hh:mm");

    @Id
    private String id;
    @Field(type = FieldType.Long)
    private Long itemId;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String itemName;
    @Field(index = FieldIndex.not_analyzed)
    private Long realmId;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String locale;
    @Field(index = FieldIndex.not_analyzed)
    private String realmName;
    @Field(type = FieldType.Double)
    private Double minimumBid;
    @Field(type = FieldType.Double)
    private Double minimumBuyout;
    @Field(type = FieldType.Double)
    private Double maximumBid;
    @Field(type = FieldType.Double)
    private Double maximumBuyout;
    @Field(type = FieldType.Long)
    private Long quantity;
    @Field(type = FieldType.Double)
    private Double averageBid;
    @Field(type = FieldType.Double)
    private Double averageBuyout;
    @Field(type = FieldType.Date,
            store = true,
            format = org.springframework.data.elasticsearch.annotations.DateFormat.basic_date_time, pattern = "dd.MM.yyyy hh:mm")
    private Date exportTime;

    public String getId() {
        return id;
    }

    public String getLocale() {
        return locale;
    }

    public HistoricAuctionHouseSnapshot setLocale(String locale) {
        this.locale = locale;
        return this;
    }

    public HistoricAuctionHouseSnapshot setId(String id) {
        this.id = id;
        return this;
    }

    public Long getItemId() {
        return itemId;
    }

    public HistoricAuctionHouseSnapshot setItemId(Long itemId) {
        this.itemId = itemId;
        return this;
    }

    public String getItemName() {
        return itemName;
    }

    public HistoricAuctionHouseSnapshot setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public Long getRealmId() {
        return realmId;
    }

    public HistoricAuctionHouseSnapshot setRealmId(Long realmId) {
        this.realmId = realmId;
        return this;
    }

    public String getRealmName() {
        return realmName;
    }

    public HistoricAuctionHouseSnapshot setRealmName(String realmName) {
        this.realmName = realmName;
        return this;
    }

    public Double getMinimumBid() {
        return minimumBid;
    }

    public HistoricAuctionHouseSnapshot setMinimumBid(Double minimumBid) {
        this.minimumBid = minimumBid;
        return this;
    }

    public Double getMinimumBuyout() {
        return minimumBuyout;
    }

    public HistoricAuctionHouseSnapshot setMinimumBuyout(Double minimumBuyout) {
        this.minimumBuyout = minimumBuyout;
        return this;
    }

    public Double getMaximumBid() {
        return maximumBid;
    }

    public HistoricAuctionHouseSnapshot setMaximumBid(Double maximumBid) {
        this.maximumBid = maximumBid;
        return this;
    }

    public Double getMaximumBuyout() {
        return maximumBuyout;
    }

    public HistoricAuctionHouseSnapshot setMaximumBuyout(Double maximumBuyout) {
        this.maximumBuyout = maximumBuyout;
        return this;
    }

    public Long getQuantity() {
        return quantity;
    }

    public HistoricAuctionHouseSnapshot setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public Double getAverageBid() {
        return averageBid;
    }

    public HistoricAuctionHouseSnapshot setAverageBid(Double averageBid) {
        this.averageBid = averageBid;
        return this;
    }

    public Double getAverageBuyout() {
        return averageBuyout;
    }

    public HistoricAuctionHouseSnapshot setAverageBuyout(Double averageBuyout) {
        this.averageBuyout = averageBuyout;
        return this;
    }

    public Date getExportTime() {
        return exportTime;
    }

    public HistoricAuctionHouseSnapshot setExportTime(Date exportTime) {
        this.exportTime = exportTime;
        return this;
    }

    public static HistoricAuctionHouseSnapshot from(AuctionHouseSnapshot auctionHouseSnapshot) {
        return new HistoricAuctionHouseSnapshot()
                .setAverageBid(auctionHouseSnapshot.getAverageBid())
                .setAverageBuyout(auctionHouseSnapshot.getAverageBuyout())
                .setRealmId(auctionHouseSnapshot.getRealm().getId())
                .setRealmName(auctionHouseSnapshot.getRealm().getName())
                .setExportTime(auctionHouseSnapshot.getExportTime())
                .setItemId(auctionHouseSnapshot.getItem().getId())
                .setItemName(auctionHouseSnapshot.getItem().getName())
                .setMaximumBid(auctionHouseSnapshot.getMaximumBid())
                .setMaximumBuyout(auctionHouseSnapshot.getMaximumBuyout())
                .setMinimumBid(auctionHouseSnapshot.getMinimumBid())
                .setMinimumBuyout(auctionHouseSnapshot.getMinimumBuyout())
                .setQuantity(auctionHouseSnapshot.getQuantity())
                .setLocale(auctionHouseSnapshot.getRealm().getLocality().getLocalityName())
                .setId(
                        auctionHouseSnapshot.getItem().getName()
                                + "-" + auctionHouseSnapshot.getRealm().getLocality().getLocalityName()
                                + "-" + auctionHouseSnapshot.getRealm().getName()
                                + "-" + format.format(auctionHouseSnapshot.getExportTime())
                );

    }
}
