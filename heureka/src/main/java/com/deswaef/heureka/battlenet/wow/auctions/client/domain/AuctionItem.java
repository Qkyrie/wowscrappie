package com.deswaef.heureka.battlenet.wow.auctions.client.domain;

import java.util.ArrayList;
import java.util.List;

public class AuctionItem {
    private Long auc;
    private Long item;
    private String owner;
    private Long bid;
    private Long buyout;
    private int quantity;
    private String timeLeft;
    private String ownerRealm;
    private Long context;
    private List<ModifierDto> modifiers = new ArrayList<>();
    private List<BonusListDto> bonusLists = new ArrayList<>();

    //pet specific things

    private long petSpeciesId;
    private long petBreedId;
    private int petLevel;
    private int petQualityId;


    public List<BonusListDto> bonusLists() {
        return bonusLists;
    }

    public AuctionItem bonusLists(List<BonusListDto> bonusLists) {
        this.bonusLists = bonusLists;
        return this;
    }

    public long petSpeciesId() {
        return petSpeciesId;
    }

    public AuctionItem petSpeciesId(long petSpeciesId) {
        this.petSpeciesId = petSpeciesId;
        return this;
    }

    public long petBreedId() {
        return petBreedId;
    }

    public AuctionItem petBreedId(long petBreedId) {
        this.petBreedId = petBreedId;
        return this;
    }

    public int petLevel() {
        return petLevel;
    }

    public AuctionItem petLevel(int petLevel) {
        this.petLevel = petLevel;
        return this;
    }

    public int petQualityId() {
        return petQualityId;
    }

    public AuctionItem petQualityId(int petQualityId) {
        this.petQualityId = petQualityId;
        return this;
    }

    public List<ModifierDto> modifiers() {
        return modifiers;
    }

    public AuctionItem modifiers(List<ModifierDto> modifiers) {
        this.modifiers = modifiers;
        return this;
    }

    public Long auc() {
        return auc;
    }

    public AuctionItem auc(Long auc) {
        this.auc = auc;
        return this;
    }

    public Long item() {
        return item;
    }

    public AuctionItem item(Long item) {
        this.item = item;
        return this;
    }

    public String owner() {
        return owner;
    }

    public AuctionItem owner(String owner) {
        this.owner = owner;
        return this;
    }

    public Long bid() {
        return bid;
    }

    public AuctionItem bid(Long bid) {
        this.bid = bid;
        return this;
    }

    public Long buyout() {
        return buyout;
    }

    public AuctionItem buyout(Long buyout) {
        this.buyout = buyout;
        return this;
    }

    public int quantity() {
        return quantity;
    }

    public AuctionItem quantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public String timeLeft() {
        return timeLeft;
    }

    public AuctionItem timeLeft(String timeLeft) {
        this.timeLeft = timeLeft;
        return this;
    }

    public String ownerRealm() {
        return ownerRealm;
    }

    public AuctionItem ownerRealm(String ownerRealm) {
        this.ownerRealm = ownerRealm;
        return this;
    }

    public Long context() {
        return context;
    }

    public AuctionItem context(Long context) {
        this.context = context;
        return this;
    }
}