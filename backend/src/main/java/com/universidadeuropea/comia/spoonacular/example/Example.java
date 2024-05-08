package com.universidadeuropea.comia.spoonacular.example;

import com.spoonacular.client.ApiClient;
import com.spoonacular.client.ApiException;
import com.spoonacular.client.Configuration;
import com.spoonacular.client.auth.*;
import com.spoonacular.client.model.AnalyzeARecipeSearchQuery200Response;

import org.springframework.beans.factory.annotation.Value;

import com.spoonacular.RecipesApi;

public class Example {

  @Value("${spoonacular.api.key}")
  private String apiKey;
  
  @Value("${spoonacular.api.base.path}")
  private String basePath;

  @Value("${spoonacular.api.language}")
  private String lang;
  
  
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();


    defaultClient.setBasePath("https://api.spoonacular.com");
    
    // Configure API key authorization: apiKeyScheme
    ApiKeyAuth apiKeyScheme = (ApiKeyAuth) defaultClient.getAuthentication("apiKeyScheme");
    apiKeyScheme.setApiKey("b407711636a04d05a7e31f2b4890c9a7");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //apiKeyScheme.setApiKeyPrefix("Token");

    RecipesApi apiInstance = new RecipesApi(defaultClient);
    String q = "salmon with fusilli and no nuts"; // String | The recipe search query.
    try {
      AnalyzeARecipeSearchQuery200Response result = apiInstance.analyzeARecipeSearchQuery(q);
      result.toJson();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling RecipesApi#analyzeARecipeSearchQuery");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}