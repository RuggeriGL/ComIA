package com.universidadeuropea.comia.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
public class RecipesResponse {

    @JsonProperty("recipes")
    private List<Recipe> recipes;

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Data
    public static class Root {
        public List<Recipe> recipes;
    }

    // Método para deserializar desde un JSON String
    public static Root parseJson(String jsonString) {
        ObjectMapper om = new ObjectMapper();
        try {
            return om.readValue(jsonString, Root.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Retorna null o maneja de otra manera
        }
    }
    
    @Data
    public class AnalyzedInstruction{
        public String name;
        public ArrayList<Step> steps;
    }
    @Data
    public class Equipment{
        public int id;
        public String name;
        public String localizedName;
        public String image;
        public Temperature temperature;
    }
    @Data
    public class ExtendedIngredient{
        public int id;
        public String aisle;
        public String image;
        public String consistency;
        public String name;
        public String nameClean;
        public String original;
        public String originalName;
        public double amount;
        public String unit;
        public ArrayList<String> meta;
        public Measures measures;
    }
    @Data
    public class Ingredient{
        public int id;
        public String name;
        public String localizedName;
        public String image;
    }
    @Data
    public class Length{
        public int number;
        public String unit;
    }
    @Data
    public class Measures{
        public Us us;
        public Metric metric;
    }
    @Data
    public class Metric{
        public double amount;
        public String unitShort;
        public String unitLong;
    }
    @Data
    public class Recipe{
        public boolean vegetarian;
        public boolean vegan;
        public boolean glutenFree;
        public boolean dairyFree;
        public boolean veryHealthy;
        public boolean cheap;
        public boolean veryPopular;
        public boolean sustainable;
        public boolean lowFodmap;
        public int weightWatcherSmartPoints;
        public String gaps;
        public int preparationMinutes;
        public int cookingMinutes;
        public int aggregateLikes;
        public int healthScore;
        public String creditsText;
        public String sourceName;
        public double pricePerServing;
        public ArrayList<ExtendedIngredient> extendedIngredients;
        public int id;
        public String title;
        public int readyInMinutes;
        public int servings;
        public String sourceUrl;
        public String image;
        public String imageType;
        public String summary;
        public ArrayList<Object> cuisines;
        public ArrayList<String> dishTypes;
        public ArrayList<String> diets;
        public ArrayList<String> occasions;
        public String instructions;
        public ArrayList<AnalyzedInstruction> analyzedInstructions;
        public Object originalId;
        public double spoonacularScore;
        public String spoonacularSourceUrl;
        public String license;
    }
    @Data
    public class Step{
        public int number;
        public String step;
        public ArrayList<Ingredient> ingredients;
        public ArrayList<Equipment> equipment;
        public Length length;
    }
    @Data
    public class Temperature{
        public double number;
        public String unit;
    }
    @Data
    public class Us{
        public double amount;
        public String unitShort;
        public String unitLong;
    }

}




