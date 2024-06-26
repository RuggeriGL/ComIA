package com.universidadeuropea.comia.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.NamedNativeQueries;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "useringredients")
@Entity
public class UserIngredient {
    @EmbeddedId
    private UserIngredientId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    @ManyToOne
    @MapsId("ingredientId")
    @JoinColumn(name = "ingredient_id")
    private VfridgeIngredient ingrediente;

}


