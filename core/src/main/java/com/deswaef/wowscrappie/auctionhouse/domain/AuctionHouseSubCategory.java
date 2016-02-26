package com.deswaef.wowscrappie.auctionhouse.domain;

import com.deswaef.wowscrappie.item.domain.Item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "auctionhouse_sub_category")
public class AuctionHouseSubCategory {

    @Id
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "slug")
    private String slug;
    @Column(name = "added_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedOn;

    @ManyToMany
    @JoinTable(
            name = "auctionhouse_subcategory_item",
            joinColumns = @JoinColumn(name = "subcategory_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id")
    )
    private List<Item> items = new ArrayList<>();

    public Long id() {
        return id;
    }

    public AuctionHouseSubCategory id(Long id) {
        this.id = id;
        return this;
    }

    public String name() {
        return name;
    }

    public AuctionHouseSubCategory name(String name) {
        this.name = name;
        return this;
    }

    public List<Item> items() {
        return items;
    }

    public AuctionHouseSubCategory items(List<Item> items) {
        this.items = items;
        return this;
    }

    public String slug() {
        return slug;
    }

    public AuctionHouseSubCategory slug(String slug) {
        this.slug = slug;
        return this;
    }

    public Date addedOn() {
        return addedOn;
    }

    public AuctionHouseSubCategory addedOn(Date addedOn) {
        this.addedOn = addedOn;
        return this;
    }
}
