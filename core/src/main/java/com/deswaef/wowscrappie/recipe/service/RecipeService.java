package com.deswaef.wowscrappie.recipe.service;

import com.deswaef.wowscrappie.item.domain.Item;
import com.deswaef.wowscrappie.recipe.domain.Recipe;
import rx.Observable;

import java.util.stream.Stream;

public interface RecipeService{
    Stream<Recipe> findByItem(Item item);

    Stream<Recipe> findByItemId(long itemId);


    Observable<Recipe> findAll();
}
