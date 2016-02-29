package com.deswaef.wowscrappie.auctionhouse.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "auction_item", type = "auction_item", shards = 1, replicas = 0)
public class ReadableAuctionItem {
    @Id
    private long bid;
    @Field(type = FieldType.Long, index = FieldIndex.not_analyzed, store = false)
    private long buyout;
    @Field(type = FieldType.Long, index = FieldIndex.not_analyzed, store = false)
    private long quantity;
    @Field(type = FieldType.Long, index = FieldIndex.not_analyzed, store = false)
    private long item;
    @Field(type = FieldType.Long, index = FieldIndex.not_analyzed, store = false)
    private long auctionId;

    public long getBid() {
        return bid;
    }

    public ReadableAuctionItem setBid(long bid) {
        this.bid = bid;
        return this;
    }

    public long getBuyout() {
        return buyout;
    }

    public ReadableAuctionItem setBuyout(long buyout) {
        this.buyout = buyout;
        return this;
    }

    public long getQuantity() {
        return quantity;
    }

    public ReadableAuctionItem setQuantity(long quantity) {
        this.quantity = quantity;
        return this;
    }

    public long getItem() {
        return item;
    }

    public ReadableAuctionItem setItem(long item) {
        this.item = item;
        return this;
    }

    public long getAuctionId() {
        return auctionId;
    }

    public ReadableAuctionItem setAuctionId(long auctionId) {
        this.auctionId = auctionId;
        return this;
    }
}
