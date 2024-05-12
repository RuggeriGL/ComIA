package com.universidadeuropea.comia.model;

import lombok.Data;
import lombok.ToString;;

@Data
@ToString
public class RecipeSearchCriteria {
    private String query;
    private String[] cuisine;
    private String[] excludeCuisine;
    private String[] diet;
    private String[] intolerances;
    private String[] equipment;
    private String[] includeIngredients;
    private String[] excludeIngredients;
    private String[] type;
    private boolean instructionsRequired;
    private boolean fillIngredients;
    private boolean addRecipeInformation;
    private boolean addRecipeNutrition;
    private String author;
    private String tags;
    private Integer recipeBoxId;
    private String titleMatch;
    private Integer maxReadyTime;
    private boolean ignorePantry;
    private String sort;
    private String sortDirection;
    private NutritionalValues nutritionalValues;
    private Pagination pagination;
    private boolean limitLicense;
    
    
}

