package com.deswaef.heureka.wowuction.client.model;

public class Entry {

    private String realm;
    private String exportTime;
    private String PMktPriceDate;
    private String reserved;
    private Long itemId;
    private String itemName;
    private Double ahMarketPrice;
    private Long ahMarketPriceCoppers;
    private Long quantity;
    private Double ahMinimumPrice;
    private Double medianMarketPrice;
    private Double marketPriceStdev;
    private Double todaysPMktPrice;
    private Double pmktPriceStdev;
    private Double dailyPriceChange;
    private Double dailyAveragePosted;
    private Double dailyAverageSold;
    private Double estimatedDemand;

    private Entry() {
    }

    public static Entry createEntry() {
        return new Entry();
    }

    public String realm() {
        return realm;
    }

    public Entry realm(String realm) {
        this.realm = realm;
        return this;
    }

    public Long ahMarketPriceCoppers() {
        return ahMarketPriceCoppers;
    }

    public Entry ahMarketPriceCoppers(Long ahMarketPriceCoppers) {
        this.ahMarketPriceCoppers = ahMarketPriceCoppers;
        return this;
    }

    public String exportTime() {
        return exportTime;
    }

    public Entry exportTime(String exportTime) {
        this.exportTime = exportTime;
        return this;
    }

    public String pMktPriceDate() {
        return PMktPriceDate;
    }

    public Entry pMktPriceDate(String PMktPriceDate) {
        this.PMktPriceDate = PMktPriceDate;
        return this;
    }

    public String reserved() {
        return reserved;
    }

    public Entry reserved(String reserved) {
        this.reserved = reserved;
        return this;
    }

    public Long itemId() {
        return itemId;
    }

    public Entry itemId(Long itemId) {
        this.itemId = itemId;
        return this;
    }

    public String itemName() {
        return itemName;
    }

    public Entry itemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public Double ahMarketPrice() {
        return ahMarketPrice;
    }

    public Entry ahMarketPrice(Double ahMarketPrice) {
        this.ahMarketPrice = ahMarketPrice;
        return this;
    }

    public Long quantity() {
        return quantity;
    }

    public Entry quantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public Double ahMinimumPrice() {
        return ahMinimumPrice;
    }

    public Entry ahMinimumPrice(Double ahMinimumPrice) {
        this.ahMinimumPrice = ahMinimumPrice;
        return this;
    }

    public Double medianMarketPrice() {
        return medianMarketPrice;
    }

    public Entry medianMarketPrice(Double medianMarketPrice) {
        this.medianMarketPrice = medianMarketPrice;
        return this;
    }

    public Double marketPriceStdev() {
        return marketPriceStdev;
    }

    public Entry marketPriceStdev(Double marketPriceStdev) {
        this.marketPriceStdev = marketPriceStdev;
        return this;
    }

    public Double todaysPMktPrice() {
        return todaysPMktPrice;
    }

    public Entry todaysPMktPrice(Double todaysPMktPrice) {
        this.todaysPMktPrice = todaysPMktPrice;
        return this;
    }

    public Double pmktPriceStdev() {
        return pmktPriceStdev;
    }

    public Entry pmktPriceStdev(Double pmktPriceStdev) {
        this.pmktPriceStdev = pmktPriceStdev;
        return this;
    }

    public Double dailyPriceChange() {
        return dailyPriceChange;
    }

    public Entry dailyPriceChange(Double dailyPriceChange) {
        this.dailyPriceChange = dailyPriceChange;
        return this;
    }

    public Double dailyAveragePosted() {
        return dailyAveragePosted;
    }

    public Entry dailyAveragePosted(Double dailyAveragePosted) {
        this.dailyAveragePosted = dailyAveragePosted;
        return this;
    }

    public Double dailyAverageSold() {
        return dailyAverageSold;
    }

    public Entry dailyAverageSold(Double dailyAverageSold) {
        this.dailyAverageSold = dailyAverageSold;
        return this;
    }

    public Double estimatedDemand() {
        return estimatedDemand;
    }

    public Entry estimatedDemand(Double estimatedDemand) {
        this.estimatedDemand = estimatedDemand;
        return this;
    }
}

