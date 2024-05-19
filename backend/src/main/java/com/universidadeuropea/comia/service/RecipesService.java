package com.universidadeuropea.comia.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.universidadeuropea.comia.dto.ProfileDto;
import com.universidadeuropea.comia.dto.UserDto;
import com.universidadeuropea.comia.entity.Favoritos;
import com.universidadeuropea.comia.entity.Profiles;
import com.universidadeuropea.comia.entity.UserFavoritoId;
import com.universidadeuropea.comia.entity.UserFavoritos;
import com.universidadeuropea.comia.entity.UserIngredient;
import com.universidadeuropea.comia.entity.UserIngredientId;
import com.universidadeuropea.comia.entity.Usuario;
import com.universidadeuropea.comia.entity.VfridgeIngredient;
import com.universidadeuropea.comia.exceptions.AppException;
import com.universidadeuropea.comia.model.Ingredient;
import com.universidadeuropea.comia.model.RecipeSearchCriteria;
import com.universidadeuropea.comia.repository.VfridgeIngredientRepository;

import jakarta.transaction.Transactional;

import com.universidadeuropea.comia.repository.FavoritosRepository;
import com.universidadeuropea.comia.repository.UserFavoritosRepository;
import com.universidadeuropea.comia.repository.UserIngredientRepository;
import com.universidadeuropea.comia.repository.UsuarioRepository;

@Service
public class RecipesService {

    @Value("${spoonacular.api.key}")
    private String apiKey;

    @Value("${spoonacular.api.base.path}")
    private String basePath;

    @Autowired
    private VfridgeIngredientRepository vfridgeIngredientRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    UserIngredientRepository userIngredientRepository;

    @Autowired
    UserFavoritosRepository userFavoritosRepository;

    @Autowired
    FavoritosRepository favoritosRepository;

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
            BufferedReader reader = new BufferedReader(new FileReader(
                    "C:\\Users\\berna\\OneDrive\\Documentos\\GitHub\\ComIA\\backend\\src\\main\\java\\com\\universidadeuropea\\comia\\spoonacular\\test\\test.json"));
            String line = null;
            StringBuilder stringBuilder = new StringBuilder();
            String ls = System.getProperty("line.separator");

            try {
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append(ls);
                }
                // System.err.println(stringBuilder.toString());
                return stringBuilder.toString();
            } finally {
                reader.close();
            }

        } catch (Exception e) {

            return "";
        }

    }

    public String getComplexSearchRecipes(RecipeSearchCriteria criteria)
            throws IOException, InterruptedException, URISyntaxException {
        HttpClient client = HttpClient.newHttpClient();

        // Construye la URL base con parámetros dinámicos
        URIBuilder builder = new URIBuilder(this.basePath + "/recipes/complexSearch");

        // Añade parámetros solo si están presentes en el objeto criteria
        if (criteria.getQuery() != null && !criteria.getQuery().isEmpty()) {
            builder.addParameter("query", criteria.getQuery());
        }
        if (criteria.getCuisine() != null && criteria.getCuisine().length > 0) {
            String ingredients = String.join(",", criteria.getCuisine());
            builder.addParameter("cuisine", ingredients);
        }
        if (criteria.getExcludeCuisine() != null && criteria.getExcludeCuisine().length > 0) {
            String ingredients = String.join(",", criteria.getExcludeCuisine());
            builder.addParameter("excludeCuisine", ingredients);
        }
        if (criteria.getDiet() != null && criteria.getDiet().length > 0) {
            String ingredients = String.join(",", criteria.getDiet());
            builder.addParameter("diet", ingredients);
        }
        if (criteria.getIntolerances() != null && criteria.getIntolerances().length > 0) {
            String ingredients = String.join(",", criteria.getIntolerances());
            builder.addParameter("intolerances", ingredients);
        }
        if (criteria.getEquipment() != null && criteria.getEquipment().length > 0) {
            String ingredients = String.join(",", criteria.getEquipment());
            builder.addParameter("equipment", ingredients);
        }
        if (criteria.getIncludeIngredients() != null && criteria.getIncludeIngredients().length > 0) {
            String ingredients = String.join(",", criteria.getIncludeIngredients());
            builder.addParameter("includeIngredients", ingredients);
        }
        if (criteria.getExcludeIngredients() != null && criteria.getExcludeIngredients().length > 0) {
            String ingredients = String.join(",", criteria.getExcludeIngredients());
            builder.addParameter("excludeIngredients", ingredients);
        }
        if (criteria.getType() != null && criteria.getType().length > 0) {
            String ingredients = String.join(",", criteria.getType());
            builder.addParameter("type", ingredients);
        }
        if (criteria.isInstructionsRequired()) {
            builder.addParameter("instructionsRequired", criteria.isInstructionsRequired() ? "true" : "false");
        }
        if (criteria.isFillIngredients()) {
            builder.addParameter("fillIngredients", criteria.isFillIngredients() ? "true" : "false");
        }
        if (criteria.isAddRecipeInformation()) {
            builder.addParameter("addRecipeInformation", criteria.isAddRecipeInformation() ? "true" : "false");
        }
        if (criteria.isAddRecipeNutrition()) {
            builder.addParameter("addRecipeNutrition", criteria.isAddRecipeNutrition() ? "true" : "false");
        }
        if (criteria.getAuthor() != null && !criteria.getAuthor().isEmpty()) {
            builder.addParameter("author", criteria.getAuthor());
        }
        if (criteria.getTags() != null && !criteria.getTags().isEmpty()) {
            builder.addParameter("tags", criteria.getTags());
        }
        if (criteria.getRecipeBoxId() != null) {
            builder.addParameter("recipeBoxId", criteria.getRecipeBoxId().toString());
        }
        if (criteria.getTitleMatch() != null && !criteria.getTitleMatch().isEmpty()) {
            builder.addParameter("titleMatch", criteria.getTitleMatch());
        }
        if (criteria.getMaxReadyTime() != null) {
            builder.addParameter("maxReadyTime", criteria.getMaxReadyTime().toString());
        }
        if (criteria.isIgnorePantry()) {
            builder.addParameter("ignorePantry", criteria.isIgnorePantry() ? "true" : "false");
        }
        if (criteria.getSort() != null && !criteria.getSort().isEmpty()) {
            builder.addParameter("sort", criteria.getSort());
        }
        if (criteria.getSortDirection() != null && !criteria.getSortDirection().isEmpty()) {
            builder.addParameter("sortDirection", criteria.getSortDirection());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMinCarbs() != null) {
            builder.addParameter("minCarbs", criteria.getNutritionalValues().getMinCarbs().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMaxCarbs() != null) {
            builder.addParameter("maxCarbs", criteria.getNutritionalValues().getMaxCarbs().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMinProtein() != null) {
            builder.addParameter("minProtein", criteria.getNutritionalValues().getMinProtein().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMaxProtein() != null) {
            builder.addParameter("maxProtein", criteria.getNutritionalValues().getMaxProtein().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMinCalories() != null) {
            builder.addParameter("minCalories", criteria.getNutritionalValues().getMinCalories().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMaxCalories() != null) {
            builder.addParameter("maxCalories", criteria.getNutritionalValues().getMaxCalories().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMinFat() != null) {
            builder.addParameter("minFat", criteria.getNutritionalValues().getMinFat().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMaxFat() != null) {
            builder.addParameter("maxFat", criteria.getNutritionalValues().getMaxFat().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMinAlcohol() != null) {
            builder.addParameter("minAlcohol", criteria.getNutritionalValues().getMinAlcohol().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMaxAlcohol() != null) {
            builder.addParameter("maxAlcohol", criteria.getNutritionalValues().getMaxAlcohol().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMinCaffeine() != null) {
            builder.addParameter("minCaffeine", criteria.getNutritionalValues().getMinCaffeine().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMaxCaffeine() != null) {
            builder.addParameter("maxCaffeine", criteria.getNutritionalValues().getMaxCaffeine().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMinCopper() != null) {
            builder.addParameter("minCopper", criteria.getNutritionalValues().getMinCopper().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMaxCopper() != null) {
            builder.addParameter("maxCopper", criteria.getNutritionalValues().getMaxCopper().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMinCalcium() != null) {
            builder.addParameter("minCalcium", criteria.getNutritionalValues().getMinCalcium().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMaxCalcium() != null) {
            builder.addParameter("maxCalcium", criteria.getNutritionalValues().getMaxCalcium().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMinCholine() != null) {
            builder.addParameter("minCholine", criteria.getNutritionalValues().getMinCholine().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMaxCholine() != null) {
            builder.addParameter("maxCholine", criteria.getNutritionalValues().getMaxCholine().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMinCholesterol() != null) {
            builder.addParameter("minCholesterol", criteria.getNutritionalValues().getMinCholesterol().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMaxCholesterol() != null) {
            builder.addParameter("maxCholesterol", criteria.getNutritionalValues().getMaxCholesterol().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMinFluoride() != null) {
            builder.addParameter("minFluoride", criteria.getNutritionalValues().getMinFluoride().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMaxFluoride() != null) {
            builder.addParameter("maxFluoride", criteria.getNutritionalValues().getMaxFluoride().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMinSaturatedFat() != null) {
            builder.addParameter("minSaturatedFat", criteria.getNutritionalValues().getMinSaturatedFat().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMaxSaturatedFat() != null) {
            builder.addParameter("maxSaturatedFat", criteria.getNutritionalValues().getMaxSaturatedFat().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMinVitaminA() != null) {
            builder.addParameter("minVitaminA", criteria.getNutritionalValues().getMinVitaminA().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMaxVitaminA() != null) {
            builder.addParameter("maxVitaminA", criteria.getNutritionalValues().getMaxVitaminA().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMinVitaminC() != null) {
            builder.addParameter("minVitaminC", criteria.getNutritionalValues().getMinVitaminC().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMaxVitaminC() != null) {
            builder.addParameter("maxVitaminC", criteria.getNutritionalValues().getMaxVitaminC().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMinVitaminD() != null) {
            builder.addParameter("minVitaminD", criteria.getNutritionalValues().getMinVitaminD().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMaxVitaminD() != null) {
            builder.addParameter("maxVitaminD", criteria.getNutritionalValues().getMaxVitaminD().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMinVitaminE() != null) {
            builder.addParameter("minVitaminE", criteria.getNutritionalValues().getMinVitaminE().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMaxVitaminE() != null) {
            builder.addParameter("maxVitaminE", criteria.getNutritionalValues().getMaxVitaminE().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMinVitaminK() != null) {
            builder.addParameter("minVitaminK", criteria.getNutritionalValues().getMinVitaminK().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMaxVitaminK() != null) {
            builder.addParameter("maxVitaminK", criteria.getNutritionalValues().getMaxVitaminK().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMinVitaminB1() != null) {
            builder.addParameter("minVitaminB1", criteria.getNutritionalValues().getMinVitaminB1().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMaxVitaminB1() != null) {
            builder.addParameter("maxVitaminB1", criteria.getNutritionalValues().getMaxVitaminB1().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMinVitaminB2() != null) {
            builder.addParameter("minVitaminB2", criteria.getNutritionalValues().getMinVitaminB2().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMaxVitaminB2() != null) {
            builder.addParameter("maxVitaminB2", criteria.getNutritionalValues().getMaxVitaminB2().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMinVitaminB5() != null) {
            builder.addParameter("minVitaminB5", criteria.getNutritionalValues().getMinVitaminB5().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMaxVitaminB5() != null) {
            builder.addParameter("maxVitaminB5", criteria.getNutritionalValues().getMaxVitaminB5().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMinVitaminB3() != null) {
            builder.addParameter("minVitaminB3", criteria.getNutritionalValues().getMinVitaminB3().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMaxVitaminB3() != null) {
            builder.addParameter("maxVitaminB3", criteria.getNutritionalValues().getMaxVitaminB3().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMinVitaminB6() != null) {
            builder.addParameter("minVitaminB6", criteria.getNutritionalValues().getMinVitaminB6().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMaxVitaminB6() != null) {
            builder.addParameter("maxVitaminB6", criteria.getNutritionalValues().getMaxVitaminB6().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMinVitaminB12() != null) {
            builder.addParameter("minVitaminB12", criteria.getNutritionalValues().getMinVitaminB12().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMaxVitaminB12() != null) {
            builder.addParameter("maxVitaminB12", criteria.getNutritionalValues().getMaxVitaminB12().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMinFiber() != null) {
            builder.addParameter("minFiber", criteria.getNutritionalValues().getMinFiber().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMaxFiber() != null) {
            builder.addParameter("maxFiber", criteria.getNutritionalValues().getMaxFiber().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMinFolate() != null) {
            builder.addParameter("minFolate", criteria.getNutritionalValues().getMinFolate().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMaxFolate() != null) {
            builder.addParameter("maxFolate", criteria.getNutritionalValues().getMaxFolate().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMinFolicAcid() != null) {
            builder.addParameter("minFolicAcid", criteria.getNutritionalValues().getMinFolicAcid().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMaxFolicAcid() != null) {
            builder.addParameter("maxFolicAcid", criteria.getNutritionalValues().getMaxFolicAcid().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMinIodine() != null) {
            builder.addParameter("minIodine", criteria.getNutritionalValues().getMinIodine().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMaxIodine() != null) {
            builder.addParameter("maxIodine", criteria.getNutritionalValues().getMaxIodine().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMinIron() != null) {
            builder.addParameter("minIron", criteria.getNutritionalValues().getMinIron().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMaxIron() != null) {
            builder.addParameter("maxIron", criteria.getNutritionalValues().getMaxIron().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMinMagnesium() != null) {
            builder.addParameter("minMagnesium", criteria.getNutritionalValues().getMinMagnesium().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMaxMagnesium() != null) {
            builder.addParameter("maxMagnesium", criteria.getNutritionalValues().getMaxMagnesium().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMinManganese() != null) {
            builder.addParameter("minManganese", criteria.getNutritionalValues().getMinManganese().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMaxManganese() != null) {
            builder.addParameter("maxManganese", criteria.getNutritionalValues().getMaxManganese().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMinPhosphorus() != null) {
            builder.addParameter("minPhosphorus", criteria.getNutritionalValues().getMinPhosphorus().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMaxPhosphorus() != null) {
            builder.addParameter("maxPhosphorus", criteria.getNutritionalValues().getMaxPhosphorus().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMinPotassium() != null) {
            builder.addParameter("minPotassium", criteria.getNutritionalValues().getMinPotassium().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMaxPotassium() != null) {
            builder.addParameter("maxPotassium", criteria.getNutritionalValues().getMaxPotassium().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMinSelenium() != null) {
            builder.addParameter("minSelenium", criteria.getNutritionalValues().getMinSelenium().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMaxSelenium() != null) {
            builder.addParameter("maxSelenium", criteria.getNutritionalValues().getMaxSelenium().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMinSodium() != null) {
            builder.addParameter("minSodium", criteria.getNutritionalValues().getMinSodium().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMaxSodium() != null) {
            builder.addParameter("maxSodium", criteria.getNutritionalValues().getMaxSodium().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMinSugar() != null) {
            builder.addParameter("minSugar", criteria.getNutritionalValues().getMinSugar().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMaxSugar() != null) {
            builder.addParameter("maxSugar", criteria.getNutritionalValues().getMaxSugar().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMinZinc() != null) {
            builder.addParameter("minZinc", criteria.getNutritionalValues().getMinZinc().toString());
        }
        if (criteria.getNutritionalValues() != null && criteria.getNutritionalValues().getMaxZinc() != null) {
            builder.addParameter("maxZinc", criteria.getNutritionalValues().getMaxZinc().toString());
        }
        if (criteria.getPagination() != null && criteria.getPagination().getOffset() != null) {
            builder.addParameter("offset", criteria.getPagination().getOffset().toString());
        }
        if (criteria.getPagination() != null && criteria.getPagination().getNumber() != null) {
            builder.addParameter("number", criteria.getPagination().getNumber().toString());
        }
        if (criteria.isLimitLicense()) {
            builder.addParameter("limitLicense", criteria.isLimitLicense() ? "true" : "false");
        }

        builder.addParameter("apiKey", this.apiKey);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(builder.build())
                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        // System.out.println("URL Requested: " + builder.build().toString());

        if (response.statusCode() == 200) {
            System.out.println(response.body());
            return response.body();
        } else {
            throw new RuntimeException("Failed to fetch data from API: " + response.statusCode());
        }
    }

    // Método para obtener ingredientes seleccionados por un usuario
    public Set<VfridgeIngredient> getSelectedIngredients(Long userId) {
        Iterable<UserIngredient> userIngredients = userIngredientRepository.findByUsuarioId(userId);
        return StreamSupport.stream(userIngredients.spliterator(), false)
                .map(UserIngredient::getIngrediente)
                .collect(Collectors.toSet());
    }

    // Método para actualizar la selección de ingredientes de un usuario
    public void updateUserIngredients(Long userId, Set<VfridgeIngredient> vfridgeIngredients) {

        // Eliminar todas las selecciones actuales del usuario
        userIngredientRepository.deleteByUsuarioId(userId);

        // Crear nuevas selecciones basadas en la lista de IDs proporcionada
        for (VfridgeIngredient vfridgeIngredient : vfridgeIngredients) {
            Long ingredientId = vfridgeIngredient.getId();
            UserIngredientId id = new UserIngredientId(userId, ingredientId);
            UserIngredient userIngredient = new UserIngredient();
            userIngredient.setId(id);
            userIngredient.setUsuario(usuarioRepository.findById(userId).orElseThrow());
            userIngredient.setIngrediente(vfridgeIngredientRepository.findById(ingredientId).orElseThrow());
            userIngredientRepository.save(userIngredient);
        }
    }

    public Set<VfridgeIngredient> getAllIngredients() {
        Iterable<VfridgeIngredient> ingredientesIterable = vfridgeIngredientRepository.findAll();
        Set<VfridgeIngredient> ingredientsSet = new HashSet<>();

        for (VfridgeIngredient vfridgeIngredient : ingredientesIterable) {
            ingredientsSet.add(vfridgeIngredient);
        }

        return ingredientsSet;
    }

    public void addFavorite(Long userid, Favoritos favorito) {
        
        //System.out.println("userid: "+userid);
        //System.out.println("favorito: "+favorito.toString());
        
        Favoritos existeFav = favoritosRepository.findById(favorito.getId()).orElse(null);
        if (existeFav==null){
            Favoritos fav = new Favoritos();
            fav.setId(favorito.getId());
            fav.setRecipeJson(favorito.getRecipeJson());
            favoritosRepository.save(fav);
    
            Favoritos favo = favoritosRepository.findById(favorito.getId()).orElse(null);
            if (favo!=null){
                System.out.println("Fav guardada");
                System.out.println(favo.toString());
                Long favoritoId = favorito.getId();
                UserFavoritoId id = new UserFavoritoId(userid, favoritoId);
                UserFavoritos userFavoritos = new UserFavoritos();
                userFavoritos.setId(id);
                userFavoritos.setUsuario(usuarioRepository.findById(userid).orElseThrow());
                userFavoritos.setFavorito(favoritosRepository.findById(favoritoId).orElseThrow());
                userFavoritosRepository.save(userFavoritos);
            }
        } else {
                System.out.println("Existe Fav");
                Long favoritoId = existeFav.getId();
                UserFavoritoId id = new UserFavoritoId(userid, favoritoId);
                UserFavoritos userFavoritos = new UserFavoritos();
                userFavoritos.setId(id);
                userFavoritos.setUsuario(usuarioRepository.findById(userid).orElseThrow());
                userFavoritos.setFavorito(favoritosRepository.findById(favoritoId).orElseThrow());
                userFavoritosRepository.save(userFavoritos);
        }
    }

    public void removeFavorite(Long userid, Favoritos favorito) {
        System.out.println("[Removing favorite] userid: "+userid+" favorito: "+favorito.toString());

        UserFavoritoId id = new UserFavoritoId(userid,favorito.getId());
        userFavoritosRepository.deleteById(id);

    }

    public String getMyFavorites(Long id) {
        Iterable<UserFavoritos> userFavoritos = userFavoritosRepository.findByUsuarioId(id);
        Set<Long> ids = new HashSet<Long>();
        for (UserFavoritos fav : userFavoritos) {
            ids.add(fav.getId().getFavoritoId());
        }
        Iterable<Favoritos> favoritos = favoritosRepository.findAllById(ids);
        List<String> favoritosJsonList = new ArrayList<>();
        for (Favoritos fav : favoritos) {
            favoritosJsonList.add(fav.getRecipeJson());
        }
        // Convertimos la lista a un único String JSON
        return "[" + String.join(",", favoritosJsonList) + "]";
    }
    
}
