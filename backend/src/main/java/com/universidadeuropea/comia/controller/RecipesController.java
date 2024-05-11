package com.universidadeuropea.comia.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.universidadeuropea.comia.model.RecipeSearchCriteria;
import com.universidadeuropea.comia.service.RecipesService;
import reactor.core.publisher.Mono;



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

        System.out.println(criteria.toString());
        
        try {
            String recipes = recipesService.getComplexSearchRecipes(criteria);
            return ResponseEntity.ok().body(recipes);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error retrieving recipes: " + e.getMessage());
        }
    }


}
