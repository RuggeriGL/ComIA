/*
 * spoonacular API
 * The spoonacular Nutrition, Recipe, and Food API allows you to access over thousands of recipes, thousands of ingredients, 800,000 food products, over 100,000 menu items, and restaurants. Our food ontology and semantic recipe search engine makes it possible to search for recipes using natural language queries, such as \"gluten free brownies without sugar\" or \"low fat vegan cupcakes.\" You can automatically calculate the nutritional information for any recipe, analyze recipe costs, visualize ingredient lists, find recipes for what's in your fridge, find recipes based on special diets, nutritional requirements, or favorite ingredients, classify recipes into types and cuisines, convert ingredient amounts, or even compute an entire meal plan. With our powerful API, you can create many kinds of food and especially nutrition apps.  Special diets/dietary requirements currently available include: vegan, vegetarian, pescetarian, gluten free, grain free, dairy free, high protein, whole 30, low sodium, low carb, Paleo, ketogenic, FODMAP, and Primal.
 *
 * The version of the OpenAPI document: 1.1
 * Contact: mail@spoonacular.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package com.spoonacular.client.model;

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.spoonacular.client.model.AnalyzeRecipeInstructions200ResponseIngredientsInner;
import com.spoonacular.client.model.AnalyzeRecipeInstructions200ResponseParsedInstructionsInner;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.spoonacular.client.JSON;

/**
 * 
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-03-08T10:10:15.275701200+01:00[Europe/Berlin]")
public class AnalyzeRecipeInstructions200Response {
  public static final String SERIALIZED_NAME_PARSED_INSTRUCTIONS = "parsedInstructions";
  @SerializedName(SERIALIZED_NAME_PARSED_INSTRUCTIONS)
  private Set<AnalyzeRecipeInstructions200ResponseParsedInstructionsInner> parsedInstructions = new LinkedHashSet<>();

  public static final String SERIALIZED_NAME_INGREDIENTS = "ingredients";
  @SerializedName(SERIALIZED_NAME_INGREDIENTS)
  private Set<AnalyzeRecipeInstructions200ResponseIngredientsInner> ingredients = new LinkedHashSet<>();

  public static final String SERIALIZED_NAME_EQUIPMENT = "equipment";
  @SerializedName(SERIALIZED_NAME_EQUIPMENT)
  private Set<AnalyzeRecipeInstructions200ResponseIngredientsInner> equipment = new LinkedHashSet<>();

  public AnalyzeRecipeInstructions200Response() {
  }

  public AnalyzeRecipeInstructions200Response parsedInstructions(Set<AnalyzeRecipeInstructions200ResponseParsedInstructionsInner> parsedInstructions) {
    this.parsedInstructions = parsedInstructions;
    return this;
  }

  public AnalyzeRecipeInstructions200Response addParsedInstructionsItem(AnalyzeRecipeInstructions200ResponseParsedInstructionsInner parsedInstructionsItem) {
    if (this.parsedInstructions == null) {
      this.parsedInstructions = new LinkedHashSet<>();
    }
    this.parsedInstructions.add(parsedInstructionsItem);
    return this;
  }

   /**
   * Get parsedInstructions
   * @return parsedInstructions
  **/
  @javax.annotation.Nonnull
  public Set<AnalyzeRecipeInstructions200ResponseParsedInstructionsInner> getParsedInstructions() {
    return parsedInstructions;
  }

  public void setParsedInstructions(Set<AnalyzeRecipeInstructions200ResponseParsedInstructionsInner> parsedInstructions) {
    this.parsedInstructions = parsedInstructions;
  }


  public AnalyzeRecipeInstructions200Response ingredients(Set<AnalyzeRecipeInstructions200ResponseIngredientsInner> ingredients) {
    this.ingredients = ingredients;
    return this;
  }

  public AnalyzeRecipeInstructions200Response addIngredientsItem(AnalyzeRecipeInstructions200ResponseIngredientsInner ingredientsItem) {
    if (this.ingredients == null) {
      this.ingredients = new LinkedHashSet<>();
    }
    this.ingredients.add(ingredientsItem);
    return this;
  }

   /**
   * Get ingredients
   * @return ingredients
  **/
  @javax.annotation.Nonnull
  public Set<AnalyzeRecipeInstructions200ResponseIngredientsInner> getIngredients() {
    return ingredients;
  }

  public void setIngredients(Set<AnalyzeRecipeInstructions200ResponseIngredientsInner> ingredients) {
    this.ingredients = ingredients;
  }


  public AnalyzeRecipeInstructions200Response equipment(Set<AnalyzeRecipeInstructions200ResponseIngredientsInner> equipment) {
    this.equipment = equipment;
    return this;
  }

  public AnalyzeRecipeInstructions200Response addEquipmentItem(AnalyzeRecipeInstructions200ResponseIngredientsInner equipmentItem) {
    if (this.equipment == null) {
      this.equipment = new LinkedHashSet<>();
    }
    this.equipment.add(equipmentItem);
    return this;
  }

   /**
   * Get equipment
   * @return equipment
  **/
  @javax.annotation.Nonnull
  public Set<AnalyzeRecipeInstructions200ResponseIngredientsInner> getEquipment() {
    return equipment;
  }

  public void setEquipment(Set<AnalyzeRecipeInstructions200ResponseIngredientsInner> equipment) {
    this.equipment = equipment;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AnalyzeRecipeInstructions200Response analyzeRecipeInstructions200Response = (AnalyzeRecipeInstructions200Response) o;
    return Objects.equals(this.parsedInstructions, analyzeRecipeInstructions200Response.parsedInstructions) &&
        Objects.equals(this.ingredients, analyzeRecipeInstructions200Response.ingredients) &&
        Objects.equals(this.equipment, analyzeRecipeInstructions200Response.equipment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(parsedInstructions, ingredients, equipment);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AnalyzeRecipeInstructions200Response {\n");
    sb.append("    parsedInstructions: ").append(toIndentedString(parsedInstructions)).append("\n");
    sb.append("    ingredients: ").append(toIndentedString(ingredients)).append("\n");
    sb.append("    equipment: ").append(toIndentedString(equipment)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }


  public static HashSet<String> openapiFields;
  public static HashSet<String> openapiRequiredFields;

  static {
    // a set of all properties/fields (JSON key names)
    openapiFields = new HashSet<String>();
    openapiFields.add("parsedInstructions");
    openapiFields.add("ingredients");
    openapiFields.add("equipment");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
    openapiRequiredFields.add("parsedInstructions");
    openapiRequiredFields.add("ingredients");
    openapiRequiredFields.add("equipment");
  }

 /**
  * Validates the JSON Element and throws an exception if issues found
  *
  * @param jsonElement JSON Element
  * @throws IOException if the JSON Element is invalid with respect to AnalyzeRecipeInstructions200Response
  */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!AnalyzeRecipeInstructions200Response.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in AnalyzeRecipeInstructions200Response is not found in the empty JSON string", AnalyzeRecipeInstructions200Response.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!AnalyzeRecipeInstructions200Response.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `AnalyzeRecipeInstructions200Response` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : AnalyzeRecipeInstructions200Response.openapiRequiredFields) {
        if (jsonElement.getAsJsonObject().get(requiredField) == null) {
          throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", requiredField, jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      // ensure the json data is an array
      if (!jsonObj.get("parsedInstructions").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `parsedInstructions` to be an array in the JSON string but got `%s`", jsonObj.get("parsedInstructions").toString()));
      }

      JsonArray jsonArrayparsedInstructions = jsonObj.getAsJsonArray("parsedInstructions");
      // validate the required field `parsedInstructions` (array)
      for (int i = 0; i < jsonArrayparsedInstructions.size(); i++) {
        AnalyzeRecipeInstructions200ResponseParsedInstructionsInner.validateJsonElement(jsonArrayparsedInstructions.get(i));
      };
      // ensure the json data is an array
      if (!jsonObj.get("ingredients").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `ingredients` to be an array in the JSON string but got `%s`", jsonObj.get("ingredients").toString()));
      }

      JsonArray jsonArrayingredients = jsonObj.getAsJsonArray("ingredients");
      // validate the required field `ingredients` (array)
      for (int i = 0; i < jsonArrayingredients.size(); i++) {
        AnalyzeRecipeInstructions200ResponseIngredientsInner.validateJsonElement(jsonArrayingredients.get(i));
      };
      // ensure the json data is an array
      if (!jsonObj.get("equipment").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `equipment` to be an array in the JSON string but got `%s`", jsonObj.get("equipment").toString()));
      }

      JsonArray jsonArrayequipment = jsonObj.getAsJsonArray("equipment");
      // validate the required field `equipment` (array)
      for (int i = 0; i < jsonArrayequipment.size(); i++) {
        AnalyzeRecipeInstructions200ResponseIngredientsInner.validateJsonElement(jsonArrayequipment.get(i));
      };
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!AnalyzeRecipeInstructions200Response.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'AnalyzeRecipeInstructions200Response' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<AnalyzeRecipeInstructions200Response> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(AnalyzeRecipeInstructions200Response.class));

       return (TypeAdapter<T>) new TypeAdapter<AnalyzeRecipeInstructions200Response>() {
           @Override
           public void write(JsonWriter out, AnalyzeRecipeInstructions200Response value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public AnalyzeRecipeInstructions200Response read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of AnalyzeRecipeInstructions200Response given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of AnalyzeRecipeInstructions200Response
  * @throws IOException if the JSON string is invalid with respect to AnalyzeRecipeInstructions200Response
  */
  public static AnalyzeRecipeInstructions200Response fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, AnalyzeRecipeInstructions200Response.class);
  }

 /**
  * Convert an instance of AnalyzeRecipeInstructions200Response to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

