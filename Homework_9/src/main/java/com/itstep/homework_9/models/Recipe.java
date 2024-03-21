package com.itstep.homework_9.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "recipes")
@Data
@EqualsAndHashCode(callSuper = true)
public class Recipe extends BaseModel {

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @Getter
    @Setter
    @Column(name = "cooking_url")
    private String cookingUrl;

    @Getter
    @Setter
    @Column(name = "ingredients")
    private String ingredients;

    @Getter
    @Setter
    @Column(name = "short_recipe")
    private String shortRecipe;

    @Getter
    @Setter
    @Column(name = "full_recipe")
    private String fullRecipe;

    @Getter
    @Setter
    @Column(name = "views")
    private int views;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuisine_id", referencedColumnName = "id")
    private Cuisine cuisine;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "meal_time")
    private MealTime mealTime;

}
