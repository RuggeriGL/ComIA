package com.universidadeuropea.comia.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class RecipesService {

    @Value("${spoonacular.api.key}")
    private String apiKey;

    @Value("${spoonacular.api.base.path}")
    private String basePath;

    public String getRecipes() throws IOException, InterruptedException, URISyntaxException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(basePath + "/recipes/random?number=10&tags=vegetarian,dessert&apiKey=" + apiKey))
                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            System.out.println(response.body());
            return response.body();
        } else {
            throw new RuntimeException("Failed to fetch data from Spoonacular: " + response.statusCode());
        }
    }

    public String getTestRecipes() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\berna\\OneDrive\\Documentos\\GitHub\\ComIA\\backend\\src\\main\\java\\com\\universidadeuropea\\comia\\spoonacular\\test\\test.json"));
            String line = null;
            StringBuilder stringBuilder = new StringBuilder();
            String ls = System.getProperty("line.separator");

            try {
                while((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append(ls);
                }
                System.err.println(stringBuilder.toString());
                return stringBuilder.toString();
            } finally {
                reader.close();
            }

        } catch (Exception e) {

            return "";
        }
         
    }
    
}
