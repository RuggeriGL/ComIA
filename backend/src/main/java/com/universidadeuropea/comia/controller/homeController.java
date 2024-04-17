package com.universidadeuropea.comia.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HomeController {

    @Value("${spoonacular.api.language}")
    private String apiLanguage;

    @Value("${spoonacular.api.key}")
    private String apiKey;

    @Value("${spoonacular.api.complexSearch.url}")
    private String complexSearchUrl;

    @GetMapping(value = {"/", "/testRecipe"})
    public String getRecipe() {
        String queryExample = "?query=pasta&maxFat=25&number=5";
        String url = complexSearchUrl + queryExample + "&apiKey=" + apiKey + "&language=" + apiLanguage;
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        System.out.println("ENVIANDO RECETA DE PRUEBA");
        return response;
    }

    
}
