package com.universidadeuropea.comia.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.universidadeuropea.comia.dto.UserDto;
import com.universidadeuropea.comia.entity.RecipeHistory;
import com.universidadeuropea.comia.model.RecipeSearchCriteria;
import com.universidadeuropea.comia.repository.RecipeHistoryRepository;
import com.universidadeuropea.comia.service.RecipesService;
import com.universidadeuropea.comia.service.UserService;

import jakarta.persistence.PersistenceException;






@RestController
public class RecipesController {

    @Value("${spoonacular.api.key}")
    private String apiKey;
    
    @Value("${spoonacular.api.base.path}")
    private String basePath;

    @Value("${spoonacular.api.language}")
    private String lang;

    @Autowired
    private RecipesService recipesService;
    @Autowired
    private UserService userService;
    @Autowired
    private RecipeHistoryRepository recipeHistoryRepository;
  

    @GetMapping(value={"/random"})
    public ResponseEntity<String> getRandomRecipes() {
        try {
            String recipes = recipesService.getRecipes();
            return ResponseEntity.ok().body(recipes);
        } catch (IOException | InterruptedException | URISyntaxException e) {
            return ResponseEntity.internalServerError().body("Error retrieving recipes: " + e.getMessage());
        }
    }
    
    @GetMapping(value={"/test"})
    public ResponseEntity<String> getTestRecipes() {
        try {
            String recipes = recipesService.getTestRecipes();
            return ResponseEntity.ok().body(recipes);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error retrieving recipes: " + e.getMessage());
        }
    }

    @PostMapping(value = {"/complexSearch"})
    public ResponseEntity<String> performComplexSearch(@RequestBody RecipeSearchCriteria criteria) {

        //System.out.println(criteria.toString());
        UserDto user = userService.whoami();
        //System.out.println(user.toString());
        RecipeHistory savedRecipeHistory = new RecipeHistory();
        Boolean savedRecipe = false;

        if(user!=null){
            RecipeHistory recipeHistory = new RecipeHistory();
            
            if(user.getId()!=null && StringUtils.isNotEmpty(user.getId().toString())){
                recipeHistory.setUserId(user.getId());
            }
            
            if(criteria.getQuery()!=null && StringUtils.isNotEmpty(criteria.getQuery().toString())){
                recipeHistory.setQuery(criteria.getQuery());
            }
            
            if(criteria.getCuisine()!=null && StringUtils.isNotEmpty(criteria.getCuisine().toString())){
                recipeHistory.setCuisine(criteria.getCuisine());
            }
            
            if(criteria.getExcludeCuisine()!=null && StringUtils.isNotEmpty(criteria.getExcludeCuisine().toString())){
                recipeHistory.setExcludeCuisine(criteria.getExcludeCuisine());
            }
            
            if(criteria.getDiet()!=null && StringUtils.isNotEmpty(criteria.getDiet().toString())){
                recipeHistory.setDiet(criteria.getDiet());
            }
            
            if(criteria.getIncludeIngredients()!=null && StringUtils.isNotEmpty(criteria.getIncludeIngredients().toString())){
                recipeHistory.setIngredients(criteria.getIncludeIngredients());
            }
            
            if(criteria.getType()!=null && StringUtils.isNotEmpty(criteria.getType().toString())){
                recipeHistory.setType(criteria.getType());
            }
            recipeHistory.setGenerationTime(new Date());
    
            try {
                savedRecipeHistory = recipeHistoryRepository.save(recipeHistory);
            } catch (DataIntegrityViolationException e) {
                System.err.println("Error de integridad de datos: " + e.getMessage());
                e.printStackTrace(); 
            } catch (PersistenceException e) {
                System.err.println("Error de persistencia: " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                System.err.println("Error desconocido al guardar la historia de recetas: " + e.getMessage());
                e.printStackTrace();
            }
            if (savedRecipe!=null){
                savedRecipe=true;
            }
        }
        
        try {
            String recipes = recipesService.getComplexSearchRecipes(criteria);
            if(savedRecipe){
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode rootNode = mapper.readTree(recipes);
                    int totalResults = rootNode.get("totalResults").asInt();
                    
                    //System.out.println("Total Results: " + totalResults);
                    savedRecipeHistory.setResults(totalResults);
                    try {
                        recipeHistoryRepository.save(savedRecipeHistory);
                    } catch (DataIntegrityViolationException e) {
                        System.err.println("Error de integridad de datos: " + e.getMessage());
                        e.printStackTrace(); 
                    } catch (PersistenceException e) {
                        System.err.println("Error de persistencia: " + e.getMessage());
                        e.printStackTrace();
                    } catch (Exception e) {
                        System.err.println("Error desconocido al guardar la historia de recetas: " + e.getMessage());
                        e.printStackTrace();
                    } 
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return ResponseEntity.ok().body(recipes);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error retrieving recipes: " + e.getMessage());
        }
    }


}
