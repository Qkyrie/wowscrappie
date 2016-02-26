package com.deswaef.wowscrappie.auctionhouse.controller.dto;

import com.deswaef.wowscrappie.auctionhouse.domain.AuctionHouseCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AuctionHouseCategoryDto {
    private Long id;
    private String name;
    private String slug;

    private List<AuctionHouseSubCategoryDto> subCategories = new ArrayList<>();

    public static AuctionHouseCategoryDto from(AuctionHouseCategory cat) {
        return new AuctionHouseCategoryDto()
                .setId(cat.id())
                .setName(cat.name())
                .setSlug(cat.slug())
                .setSubCategories(
                        cat.subCategories().stream().map(AuctionHouseSubCategoryDto::from).collect(Collectors.toList())
                );
    }

    public Long getId() {
        return id;
    }

    public AuctionHouseCategoryDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AuctionHouseCategoryDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getSlug() {
        return slug;
    }

    public AuctionHouseCategoryDto setSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public List<AuctionHouseSubCategoryDto> getSubCategories() {
        return subCategories;
    }

    public AuctionHouseCategoryDto setSubCategories(List<AuctionHouseSubCategoryDto> subCategories) {
        this.subCategories = subCategories;
        return this;
    }
}
