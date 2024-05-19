package com.universidadeuropea.comia.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.universidadeuropea.comia.entity.Favoritos;


@RepositoryRestResource(path = "usuarios")
public interface FavoritosRepository extends CrudRepository<Favoritos, Long> {

}
