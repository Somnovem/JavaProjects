package com.itstep.homework_9.controllers;

import com.itstep.homework_9.models.Category;
import com.itstep.homework_9.models.MealTime;
import com.itstep.homework_9.models.Recipe;
import com.itstep.homework_9.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping
    public List<Recipe> getAll() {
        return recipeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Recipe> getById(@PathVariable("id") Long id) {
        return recipeRepository.findById(id);
    }

    @GetMapping("/getShort?{id}")
    public String getShortRecipeById(@PathVariable("id") Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if(recipe.isPresent()) return recipe.get().getShortRecipe();
        else return "No such Id exists.";
    }

    @GetMapping("/getFull?{id}")
    public String getFullRecipeById(@PathVariable("id") Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if(recipe.isPresent()){
            Random random = new Random();
            return random.nextBoolean() ? recipe.get().getFullRecipe() : recipe.get().getCookingUrl();
        }
        else return "No such Id exists.";
    }

    @GetMapping("/getRecipeByIngredients?ingredients")
    public Optional<Recipe> getRecipeByIngredients(@PathVariable("ingredients") String ingredients) {
        List<Recipe> recipes = recipeRepository.findAll();
        String[] ingredientsArr = ingredients.split(",");
        Recipe recipeMatch = null;
        for (Recipe recipe : recipes) {
            String[] currentIngredientsArr = recipe.getIngredients().split(",");
            int matches = 0;
            for (String ingredient : ingredientsArr) {
                for (String currentIngredient : currentIngredientsArr) {
                    if (ingredient.trim().equals(currentIngredient.trim()))
                        ++matches;
                }
            }
            if (matches == ingredientsArr.length) {
                recipeMatch = recipe;
                break;
            }
        }
        return Optional.ofNullable(recipeMatch);
    }

    @GetMapping("/getRandomRecipe")
    public Recipe getRandomRecipe() {
        List<Recipe> recipes = recipeRepository.findAll();
        Random random = new Random();
        return recipes.get(random.nextInt(0,recipes.size()));
    }

    @GetMapping("/getTop3InCategoryById?{id}")
    public List<Recipe> getTop3RecipesByViewsPerCategory(long categoryId) {
        return recipeRepository.findTop3ByCategoryIdOrderByViewsDesc(categoryId);
    }

    @GetMapping("/getRandomLunchOf3")
    public List<Recipe> getRandomLunchOf3() {
        List<Recipe> lunchRecipes = recipeRepository.findAll().stream()
                .filter(recipe -> recipe.getMealTime() == MealTime.LUNCH)
                .collect(Collectors.toList());

        Collections.shuffle(lunchRecipes);

        return lunchRecipes.subList(0, Math.min(3, lunchRecipes.size()));
    }

    @GetMapping("/getRandomBreakfast")
    public Recipe getRandomBreakfast() {
        List<Recipe> recipes = recipeRepository.findAll().stream()
                .filter(recipe -> recipe.getMealTime() == MealTime.LUNCH).toList();
        Random random = new Random();
        return recipes.get(random.nextInt(0,recipes.size()));
    }

    @GetMapping("/get10RecipesWithoutIngredients?{ingrdients}")
    public Optional<List<Recipe>> get10RecipesWithoutIngredients(@PathVariable("ingredients") String ingredients) {
        List<Recipe> recipes = recipeRepository.findAll();
        String[] ingredientsArr = ingredients.split(",");
        List<Recipe> recipeMatches = new ArrayList<>();
        for (Recipe recipe : recipes) {
            String[] currentIngredientsArr = recipe.getIngredients().split(",");
            int matches = 0;
            for (String ingredient : ingredientsArr) {
                boolean match = false;
                for (String currentIngredient : currentIngredientsArr) {
                    if (ingredient.trim().equals(currentIngredient.trim()))
                        match = true;
                }
                if(!match) ++matches;
            }
            if (matches == ingredientsArr.length && recipeMatches.size() < 10) {
                recipeMatches.add(recipe);
            }
        }
        return Optional.of(recipeMatches);
    }

    @GetMapping("/getTop3InCuisineById?{id}")
    public List<Recipe> getTop3InCuisineById(long cuisineId) {
        return recipeRepository.findTop3ByCuisineIdOrderByViewsDesc(cuisineId);
    }
}
