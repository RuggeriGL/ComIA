package com.universidadeuropea.comia.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Entity
@Table(name = "useringredients")
@NamedNativeQueries({
    @NamedNativeQuery(
        name = "UserIngredient.findIngredientsByUserId",
        query = "SELECT i.* FROM ingredients i left join useringredients ui on ui.user_id = i.id where ui.user_id = :userId",
        resultClass = VfridgeIngredient.class
    )
})
public class UserIngredient implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "ingredient_id")
    private Long ingredientId;
}


