package com.itstep.homework_9.controllers;

import com.itstep.homework_9.models.Category;
import com.itstep.homework_9.models.Recipe;
import com.itstep.homework_9.repositories.CategoryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/recipe")
public class CategoryController {
    private final EntityManager entityManager;
    public CategoryController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Category> getById(@PathVariable("id") Long id) {
        return categoryRepository.findById(id);
    }

    @GetMapping("/getTop3CategoriesWithMostRecipes")
    public List<Category> getTop3CategoriesWithMostRecipes() {
        String jpql = "SELECT c FROM Category c " +
                "LEFT JOIN c.recipes r " +
                "GROUP BY c " +
                "ORDER BY COUNT(r) DESC";

        Query query = entityManager.createQuery(jpql);
        query.setMaxResults(3);

        return query.getResultList();
    }

}
