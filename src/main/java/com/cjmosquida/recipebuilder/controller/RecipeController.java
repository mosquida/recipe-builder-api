package com.cjmosquida.recipebuilder.controller;

import com.cjmosquida.recipebuilder.model.Recipe;
import com.cjmosquida.recipebuilder.repository.RecipeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/recipe")
@CrossOrigin
public class RecipeController {

    private RecipeRepository recipeRepo;

    // Injects RecipeRepository bean/instance from ioc container to constructor
    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepo = recipeRepository;
    }

    @GetMapping("")
    public List<Recipe> findAll() {
        return recipeRepo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Recipe> findById(@PathVariable Long id) {
        return Optional.ofNullable(
                recipeRepo.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
        );
    }

    @PostMapping
    public Recipe create(@RequestBody Recipe recipe) {

        return recipeRepo.save(recipe);
    }

    @PutMapping("/{id}")
    public Recipe update(@PathVariable Long id, @RequestBody Recipe newRecipe) {
        if(!recipeRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Optional<Recipe> existingRecipe = recipeRepo.findById(id);
        

        Recipe _existingRecipe = existingRecipe.get();

        _existingRecipe.setDish(newRecipe.getDish());
        _existingRecipe.setDescription(newRecipe.getDescription());
        _existingRecipe.setContent(newRecipe.getContent());
        return recipeRepo.save(_existingRecipe);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        recipeRepo.deleteById(id);
    }

}
