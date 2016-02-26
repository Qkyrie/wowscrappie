package com.deswaef.wowscrappie.auctionhouse.controller.dto;

import com.deswaef.wowscrappie.auctionhouse.domain.AuctionHouseSubCategory;

public class AuctionHouseSubCategoryDto {
    private Long id;
    private String name;
    private String slug;

    public static AuctionHouseSubCategoryDto from(AuctionHouseSubCategory cat) {
        return new AuctionHouseSubCategoryDto()
                .setId(cat.id())
                .setName(cat.name())
                .setSlug(cat.slug());
    }

    public Long getId() {
        return id;
    }

    public AuctionHouseSubCategoryDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AuctionHouseSubCategoryDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getSlug() {
        return slug;
    }

    public AuctionHouseSubCategoryDto setSlug(String slug) {
        this.slug = slug;
        return this;
    }
}
