package com.deswaef.wowscrappie.recipe.repository;

import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;
import com.deswaef.wowscrappie.item.domain.Item;
import com.deswaef.wowscrappie.recipe.domain.Recipe;
import org.springframework.data.repository.query.Param;

import java.util.stream.Stream;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Stream<Recipe> findAllByCreatesItem(@Param("createsItem") Item item);
}
