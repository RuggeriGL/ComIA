package com.universidadeuropea.comia.entity;

import java.util.Date;

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
}
