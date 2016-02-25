package com.deswaef.wowscrappie.auctionhouse.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Table(name = "auctionhouse_category")
@Entity
public class AuctionHouseCategory {

    @Id
    private Long id;

    @Column(name = "slug")
    private String slug;
    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private List<AuctionHouseSubCategory> subCategories = new ArrayList<>();

    public Long id() {
        return id;
    }

    public AuctionHouseCategory id(Long id) {
        this.id = id;
        return this;
    }

    public String slug() {
        return slug;
    }

    public AuctionHouseCategory slug(String slug) {
        this.slug = slug;
        return this;
    }

    public String name() {
        return name;
    }

    public AuctionHouseCategory name(String name) {
        this.name = name;
        return this;
    }

    public List<AuctionHouseSubCategory> subCategories() {
        return subCategories;
    }

    public AuctionHouseCategory subCategories(List<AuctionHouseSubCategory> subCategories) {
        this.subCategories = subCategories;
        return this;
    }
}
