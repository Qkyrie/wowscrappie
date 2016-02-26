package com.deswaef.wowscrappie.recipe.controller;

import com.deswaef.wowscrappie.recipe.controller.dto.RecipeDto;
import com.deswaef.wowscrappie.recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/rest/recipes")
public class RecipeRestController {

    @Autowired
    private RecipeService recipeService;

    @RequestMapping(method = GET)
    private DeferredResult<List<RecipeDto>> findAll() {
        DeferredResult<List<RecipeDto>> result = new DeferredResult<>();
        recipeService
                .findAll()
                .map(RecipeDto::from)
                .toList()
                .subscribe(result::setResult);
        return result;
    }

}
