package com.deswaef.wowscrappie.auctionhouse.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "daily_auction_snapshot", type = "daily_auction_snapshot", replicas = 0, shards = 1)
public class DailyAuctionSnapshot {

    @Id
    private String id;
    @Field(type = FieldType.Long, index = FieldIndex.not_analyzed )
    private Long itemId;
    @Field(type = FieldType.Long, index = FieldIndex.not_analyzed )
    private Long realmId;
    @Field(type = FieldType.Double, index = FieldIndex.not_analyzed )
    private Double minimumBid;
    @Field(type = FieldType.Double, index = FieldIndex.not_analyzed )
    private Double minimumBuyout;
    @Field(type = FieldType.Double, index = FieldIndex.not_analyzed )
    private Double maximumBid;
    @Field(type = FieldType.Double, index = FieldIndex.not_analyzed )
    private Double maximumBuyout;
    @Field(type = FieldType.Long, index = FieldIndex.not_analyzed )
    private Long quantity;
    @Field(type = FieldType.Double, index = FieldIndex.not_analyzed )
    private Double averageBid;
    @Field(type = FieldType.Double, index = FieldIndex.not_analyzed )
    private Double averageBuyout;
    @Field(type = FieldType.Double, index = FieldIndex.not_analyzed )
    private Double stdevBid;
    @Field(type = FieldType.Double, index = FieldIndex.not_analyzed )
    private Double stdevBuyout;
    @Field(type = FieldType.Double, index = FieldIndex.not_analyzed )
    private Double medianBid;
    @Field(type = FieldType.Double, index = FieldIndex.not_analyzed )
    private Double medianBuyout;
    @Field(type = FieldType.Date, format = DateFormat.basic_date, index = FieldIndex.not_analyzed )
    private Date exportTime;

    public String getId() {
        return id;
    }

    public DailyAuctionSnapshot setId(String id) {
        this.id = id;
        return this;
    }

    public Long getItemId() {
        return itemId;
    }

    public DailyAuctionSnapshot setItemId(Long itemId) {
        this.itemId = itemId;
        return this;
    }

    public Long getRealmId() {
        return realmId;
    }

    public DailyAuctionSnapshot setRealmId(Long realmId) {
        this.realmId = realmId;
        return this;
    }


    public Double getMinimumBid() {
        return minimumBid;
    }

    public DailyAuctionSnapshot setMinimumBid(Double minimumBid) {
        this.minimumBid = minimumBid;
        return this;
    }

    public Double getMinimumBuyout() {
        return minimumBuyout;
    }

    public DailyAuctionSnapshot setMinimumBuyout(Double minimumBuyout) {
        this.minimumBuyout = minimumBuyout;
        return this;
    }

    public Double getMaximumBid() {
        return maximumBid;
    }

    public DailyAuctionSnapshot setMaximumBid(Double maximumBid) {
        this.maximumBid = maximumBid;
        return this;
    }

    public Double getMaximumBuyout() {
        return maximumBuyout;
    }

    public DailyAuctionSnapshot setMaximumBuyout(Double maximumBuyout) {
        this.maximumBuyout = maximumBuyout;
        return this;
    }

    public Long getQuantity() {
        return quantity;
    }

    public DailyAuctionSnapshot setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public Double getAverageBid() {
        return averageBid;
    }

    public DailyAuctionSnapshot setAverageBid(Double averageBid) {
        this.averageBid = averageBid;
        return this;
    }

    public Double getAverageBuyout() {
        return averageBuyout;
    }

    public DailyAuctionSnapshot setAverageBuyout(Double averageBuyout) {
        this.averageBuyout = averageBuyout;
        return this;
    }

    public Double getStdevBid() {
        return stdevBid;
    }

    public DailyAuctionSnapshot setStdevBid(Double stdevBid) {
        this.stdevBid = stdevBid;
        return this;
    }

    public Double getStdevBuyout() {
        return stdevBuyout;
    }

    public DailyAuctionSnapshot setStdevBuyout(Double stdevBuyout) {
        this.stdevBuyout = stdevBuyout;
        return this;
    }

    public Double getMedianBid() {
        return medianBid;
    }

    public DailyAuctionSnapshot setMedianBid(Double medianBid) {
        this.medianBid = medianBid;
        return this;
    }

    public Double getMedianBuyout() {
        return medianBuyout;
    }

    public DailyAuctionSnapshot setMedianBuyout(Double medianBuyout) {
        this.medianBuyout = medianBuyout;
        return this;
    }

    public Date getExportTime() {
        return exportTime;
    }

    public DailyAuctionSnapshot setExportTime(Date exportTime) {
        this.exportTime = exportTime;
        return this;
    }
}
