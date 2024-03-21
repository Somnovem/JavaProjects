package com.itstep.homework_9.repositories;

import com.itstep.homework_9.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findTop3ByCategoryIdOrderByViewsDesc(long categoryId);

    List<Recipe> findTop3ByCuisineIdOrderByViewsDesc(long cuisineId);
}
