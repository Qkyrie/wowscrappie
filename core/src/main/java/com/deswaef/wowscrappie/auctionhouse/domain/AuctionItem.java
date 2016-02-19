package com.deswaef.wowscrappie.auctionhouse.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(indexName = "auction_item", type = "auction_item", shards = 1, replicas = 0)
public class AuctionItem {

    @Id
    private long id;
    @Field(type = FieldType.Long, index = FieldIndex.not_analyzed, store = true)
    private long item;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String owner;
    @Field(type = FieldType.Long, index = FieldIndex.not_analyzed, store = true)
    private long bid;
    @Field(type = FieldType.Long, index = FieldIndex.not_analyzed, store = true)
    private long buyout;
    @Field(type = FieldType.Integer, index = FieldIndex.not_analyzed, store = true)
    private int quantity;
    @Field(type = FieldType.Date, format = org.springframework.data.elasticsearch.annotations.DateFormat.custom, pattern = "dd.MM.yyyy hh:mm", store = true)
    private Date exportTime;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String timeLeft;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String ownerRealm;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String locality;
    @Field(type = FieldType.Long, index = FieldIndex.not_analyzed, store = true)
    private long context;
    @Field(type = FieldType.Nested)
    private List<AuctionItemModifier> modifiers = new ArrayList<>();
    private List<AuctionItemBonusList> bonusLists = new ArrayList<>();

    @Field(type = FieldType.Long, index = FieldIndex.not_analyzed, store = true)
    private long petSpeciesId;
    @Field(type = FieldType.Long, index = FieldIndex.not_analyzed, store = true)
    private long petBreedId;
    @Field(type = FieldType.Integer, index = FieldIndex.not_analyzed, store = true)
    private int petLevel;
    @Field(type = FieldType.Integer, index = FieldIndex.not_analyzed, store = true)
    private int petQualityId;

    public long getId() {
        return id;
    }

    public AuctionItem setId(long id) {
        this.id = id;
        return this;
    }

    public long getItem() {
        return item;
    }

    public AuctionItem setItem(long item) {
        this.item = item;
        return this;
    }

    public String getOwner() {
        return owner;
    }

    public AuctionItem setOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public long getBid() {
        return bid;
    }

    public AuctionItem setBid(long bid) {
        this.bid = bid;
        return this;
    }

    public long getBuyout() {
        return buyout;
    }

    public AuctionItem setBuyout(long buyout) {
        this.buyout = buyout;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public AuctionItem setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public Date getExportTime() {
        return exportTime;
    }

    public AuctionItem setExportTime(Date exportTime) {
        this.exportTime = exportTime;
        return this;
    }

    public String getTimeLeft() {
        return timeLeft;
    }

    public AuctionItem setTimeLeft(String timeLeft) {
        this.timeLeft = timeLeft;
        return this;
    }

    public String getOwnerRealm() {
        return ownerRealm;
    }

    public AuctionItem setOwnerRealm(String ownerRealm) {
        this.ownerRealm = ownerRealm;
        return this;
    }

    public String getLocality() {
        return locality;
    }

    public AuctionItem setLocality(String locality) {
        this.locality = locality;
        return this;
    }

    public long getContext() {
        return context;
    }

    public AuctionItem setContext(long context) {
        this.context = context;
        return this;
    }

    public List<AuctionItemModifier> getModifiers() {
        return modifiers;
    }

    public AuctionItem setModifiers(List<AuctionItemModifier> modifiers) {
        this.modifiers = modifiers;
        return this;
    }

    public List<AuctionItemBonusList> getBonusLists() {
        return bonusLists;
    }

    public AuctionItem setBonusLists(List<AuctionItemBonusList> bonusLists) {
        this.bonusLists = bonusLists;
        return this;
    }

    public long getPetSpeciesId() {
        return petSpeciesId;
    }

    public AuctionItem setPetSpeciesId(long petSpeciesId) {
        this.petSpeciesId = petSpeciesId;
        return this;
    }

    public long getPetBreedId() {
        return petBreedId;
    }

    public AuctionItem setPetBreedId(long petBreedId) {
        this.petBreedId = petBreedId;
        return this;
    }

    public int getPetLevel() {
        return petLevel;
    }

    public AuctionItem setPetLevel(int petLevel) {
        this.petLevel = petLevel;
        return this;
    }

    public int getPetQualityId() {
        return petQualityId;
    }

    public AuctionItem setPetQualityId(int petQualityId) {
        this.petQualityId = petQualityId;
        return this;
    }
}
