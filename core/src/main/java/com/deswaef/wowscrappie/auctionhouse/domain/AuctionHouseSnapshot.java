package com.deswaef.wowscrappie.auctionhouse.domain;

import com.deswaef.wowscrappie.item.domain.Item;
import com.deswaef.wowscrappie.realm.domain.Realm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "auctionhousesnapshot")
public class AuctionHouseSnapshot {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "realm_id")
    private Realm realm;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "minimum_bid")
    private Double minimumBid;
    @Column(name = "minimum_buyout")
    private Double minimumBuyout;
    @Column(name = "maximum_bid")
    private Double maximumBid;
    @Column(name = "maximum_buyout")
    private Double maximumBuyout;
    @Column(name = "quantity")
    private Long quantity;
    @Column(name = "average_bid")
    private Double averageBid;
    @Column(name = "average_buyout")
    private Double averageBuyout;
    @Column(name = "exportTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date exportTime;

    public Long getId() {
        return id;
    }

    public AuctionHouseSnapshot setId(Long id) {
        this.id = id;
        return this;
    }

    public Realm getRealm() {
        return realm;
    }

    public AuctionHouseSnapshot setRealm(Realm realm) {
        this.realm = realm;
        return this;
    }

    public Item getItem() {
        return item;
    }

    public AuctionHouseSnapshot setItem(Item item) {
        this.item = item;
        return this;
    }

    public Double getMinimumBid() {
        return minimumBid;
    }

    public AuctionHouseSnapshot setMinimumBid(Double minimumBid) {
        this.minimumBid = minimumBid;
        return this;
    }

    public Double getMinimumBuyout() {
        return minimumBuyout;
    }

    public AuctionHouseSnapshot setMinimumBuyout(Double minimumBuyout) {
        this.minimumBuyout = minimumBuyout;
        return this;
    }

    public Double getMaximumBid() {
        return maximumBid;
    }

    public AuctionHouseSnapshot setMaximumBid(Double maximumBid) {
        this.maximumBid = maximumBid;
        return this;
    }

    public Double getMaximumBuyout() {
        return maximumBuyout;
    }

    public AuctionHouseSnapshot setMaximumBuyout(Double maximumBuyout) {
        this.maximumBuyout = maximumBuyout;
        return this;
    }

    public Long getQuantity() {
        return quantity;
    }

    public AuctionHouseSnapshot setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public Double getAverageBid() {
        return averageBid;
    }

    public AuctionHouseSnapshot setAverageBid(Double averageBid) {
        this.averageBid = averageBid;
        return this;
    }

    public Double getAverageBuyout() {
        return averageBuyout;
    }

    public AuctionHouseSnapshot setAverageBuyout(Double averageBuyout) {
        this.averageBuyout = averageBuyout;
        return this;
    }

    public Date getExportTime() {
        return exportTime;
    }

    public AuctionHouseSnapshot setExportTime(Date exportTime) {
        this.exportTime = exportTime;
        return this;
    }

}
