package com.deswaef.wowscrappie.recipe.controller.dto;

import com.deswaef.wowscrappie.item.domain.Item;
import com.deswaef.wowscrappie.recipe.domain.RecipeItem;

public class RecipeItemDto {
    private Long id;
    private Item item;
    private Long amount;

    public static RecipeItemDto from(RecipeItem ri) {
        return new RecipeItemDto()
                .setId(ri.id())
                .setAmount(ri.amount())
                .setItem(ri.item());
    }

    public Long getId() {
        return id;
    }

    public RecipeItemDto setId(Long id) {
        this.id = id;
        return this;
    }

    public Item getItem() {
        return item;
    }

    public RecipeItemDto setItem(Item item) {
        this.item = item;
        return this;
    }

    public Long getAmount() {
        return amount;
    }

    public RecipeItemDto setAmount(Long amount) {
        this.amount = amount;
        return this;
    }
}
