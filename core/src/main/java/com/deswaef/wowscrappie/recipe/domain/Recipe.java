package com.deswaef.wowscrappie.recipe.domain;

import com.deswaef.wowscrappie.item.domain.Item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "creates_item_id")
    private Item createsItem;

    @Column(name = "creates_amount")
    private long createsAmount;

    @OneToMany(mappedBy = "recipe")
    private List<RecipeItem> recipeItems = new ArrayList<>();

    public Long id() {
        return id;
    }

    public Recipe id(Long id) {
        this.id = id;
        return this;
    }

    public String name() {
        return name;
    }

    public Recipe name(String name) {
        this.name = name;
        return this;
    }

    public Item createsItem() {
        return createsItem;
    }

    public Recipe createsItem(Item createsItem) {
        this.createsItem = createsItem;
        return this;
    }

    public long createsAmount() {
        return createsAmount;
    }

    public Recipe createsAmount(long createsAmount) {
        this.createsAmount = createsAmount;
        return this;
    }

    public List<RecipeItem> recipeItems() {
        return recipeItems;
    }

    public Recipe recipeItems(List<RecipeItem> recipeItems) {
        this.recipeItems = recipeItems;
        return this;
    }
}
