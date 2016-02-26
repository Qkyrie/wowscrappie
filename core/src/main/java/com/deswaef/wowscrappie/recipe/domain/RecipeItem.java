package com.deswaef.wowscrappie.recipe.domain;

import com.deswaef.wowscrappie.item.domain.Item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "recipe_item")
public class RecipeItem {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "amount")
    private Long amount;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public Recipe recipe() {
        return recipe;
    }

    public RecipeItem recipe(Recipe recipe) {
        this.recipe = recipe;
        return this;
    }

    public Long id() {
        return id;
    }

    public RecipeItem id(Long id) {
        this.id = id;
        return this;
    }

    public Item item() {
        return item;
    }

    public RecipeItem item(Item item) {
        this.item = item;
        return this;
    }

    public Long amount() {
        return amount;
    }

    public RecipeItem amount(Long amount) {
        this.amount = amount;
        return this;
    }
}
