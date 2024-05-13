package com.universidadeuropea.comia.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.universidadeuropea.comia.entity.UserIngredient;
import com.universidadeuropea.comia.entity.VfridgeIngredient;




@RepositoryRestResource(path = "ingredients")
public interface VfridgeIngredientRepository extends CrudRepository<VfridgeIngredient, Long> {

}

