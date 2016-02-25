package com.deswaef.wowscrappie.auctionhouse.domain;

import com.deswaef.wowscrappie.item.domain.Item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "auctionhouse_subcategory")
public class AuctionHouseSubCategory {

    @Id
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "slug")
    private String slug;

    @ManyToMany
    @JoinTable(
            name = "auctionhouse_subcategory_item",
            joinColumns = @JoinColumn(name = "subcategory_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id")
    )
    private List<Item> items;

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
}
