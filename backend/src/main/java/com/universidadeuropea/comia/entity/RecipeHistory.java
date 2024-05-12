package com.universidadeuropea.comia.entity;

import java.util.Date;

import com.universidadeuropea.comia.utils.JsonUtil;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "recipe_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RecipeHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id")
    private Long userId; 

    @Column(name = "query")
    private String query; 

    @Column(name = "cuisine")
    private String cuisine; 

    @Column(name = "exclude_cuisine")
    private String excludeCuisine; 

    @Column(name = "diet")
    private String diet; 

    @Column(name = "ingredients")
    private String ingredients; 

    @Column(name = "type")
    private String type; 

    @Column(name = "generation_time")
    private Date generationTime; 

    @Column(name = "results")
    private Integer results; 

    public void setCuisine(String[] cuisine) {
        this.cuisine = JsonUtil.toJsonString(cuisine);
    }

    public String[] getCuisine() {
        return JsonUtil.fromJsonString(cuisine);
    }

    public void setExcludeCuisine(String[] excludeCuisine) {
        this.excludeCuisine = JsonUtil.toJsonString(excludeCuisine);
    }

    public String[] getExcludeCuisine() {
        return JsonUtil.fromJsonString(excludeCuisine);
    }

    public void setDiet(String[] diet) {
        this.diet = JsonUtil.toJsonString(diet);
    }

    public String[] getDiets() {
        return JsonUtil.fromJsonString(diet);
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = JsonUtil.toJsonString(ingredients);
    }

    public String[] getIngredients() {
        return JsonUtil.fromJsonString(ingredients);
    }

    public void setType(String[] type) {
        this.type = JsonUtil.toJsonString(type);
    }

    public String[] getType() {
        return JsonUtil.fromJsonString(type);
    }



}
