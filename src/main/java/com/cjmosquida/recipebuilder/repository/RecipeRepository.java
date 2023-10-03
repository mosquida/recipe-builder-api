package com.cjmosquida.recipebuilder.repository;

import com.cjmosquida.recipebuilder.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
