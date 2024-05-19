package com.universidadeuropea.comia.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class UserIngredientId implements Serializable {
    private Long userId;
    private Long ingredientId; 
}

