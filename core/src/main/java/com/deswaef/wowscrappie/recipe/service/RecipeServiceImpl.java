package com.deswaef.wowscrappie.recipe.service;

import com.deswaef.wowscrappie.item.domain.Item;
import com.deswaef.wowscrappie.item.service.ItemService;
import com.deswaef.wowscrappie.recipe.domain.Recipe;
import com.deswaef.wowscrappie.recipe.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rx.Observable;

import java.util.Optional;
import java.util.stream.Stream;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private ItemService itemService;

    @Override
    @Transactional(readOnly = true)
    public Stream<Recipe> findByItem(Item item) {
        return recipeRepository.findAllByCreatesItem(item);
    }

    @Override
    @Transactional(readOnly = true)
    public Stream<Recipe> findByItemId(long itemId) {
        Optional<Item> one = itemService.findOne(itemId);
        if (one.isPresent()) {
            return recipeRepository.findAllByCreatesItem(one.get());
        } else {
            return Stream.empty();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Observable<Recipe> findAll() {
        return Observable.from(recipeRepository.findAll());
    }

}
