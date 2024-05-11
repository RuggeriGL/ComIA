package com.universidadeuropea.comia.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.universidadeuropea.comia.entity.RecipeHistory;
import java.util.Optional;


@RepositoryRestResource(path = "recipe_history")
public interface RecipeHistoryRepository extends CrudRepository<RecipeHistory, Long> {

    Optional<RecipeHistory> findByUserId(Long userId);
    RecipeHistory save(RecipeHistory recipeHistory);
}
