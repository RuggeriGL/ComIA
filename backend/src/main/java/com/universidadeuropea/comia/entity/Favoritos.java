package com.universidadeuropea.comia.entity;

import java.util.HashSet;
import java.util.Set;

import com.universidadeuropea.comia.utils.JsonUtil;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "favoritos")
public class Favoritos {

    @Id
    private Long id;

    @Column(name = "recipe_json")
    private String recipeJson;

    @OneToMany(mappedBy = "favorito")
    private Set<UserFavoritos> userFavoritos = new HashSet<>();

    /* 
    public void setRecipeJson(String recipeJson) {
        this.recipeJson = JsonUtil.toJsonString(recipeJson);
    }

    public String getRecipeJson() {
        return this.recipeJson;
    }
    */
}
