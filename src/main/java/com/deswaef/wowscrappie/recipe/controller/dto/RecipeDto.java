package com.deswaef.wowscrappie.recipe.controller.dto;

import com.deswaef.wowscrappie.item.domain.Item;
import com.deswaef.wowscrappie.recipe.domain.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecipeDto {
    private Long id;
    private String name;
    private Item createsItem;
    private long createsAmount;
    private List<RecipeItemDto> recipeItems = new ArrayList<>();

    public static RecipeDto from(Recipe r) {
        return new RecipeDto()
                .setId(r.id())
                .setCreatesItem(r.createsItem())
                .setName(r.name())
                .setCreatesAmount(r.createsAmount())
                .setRecipeItems(
                        r.recipeItems().stream().map(RecipeItemDto::from).collect(Collectors.toList())
                );
    }

    public Long getId() {
        return id;
    }

    public RecipeDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RecipeDto setName(String name) {
        this.name = name;
        return this;
    }

    public Item getCreatesItem() {
        return createsItem;
    }

    public RecipeDto setCreatesItem(Item createsItem) {
        this.createsItem = createsItem;
        return this;
    }

    public long getCreatesAmount() {
        return createsAmount;
    }

    public RecipeDto setCreatesAmount(long createsAmount) {
        this.createsAmount = createsAmount;
        return this;
    }

    public List<RecipeItemDto> getRecipeItems() {
        return recipeItems;
    }

    public RecipeDto setRecipeItems(List<RecipeItemDto> recipeItems) {
        this.recipeItems = recipeItems;
        return this;
    }
}
