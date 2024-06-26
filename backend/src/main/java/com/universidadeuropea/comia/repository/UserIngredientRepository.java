package com.universidadeuropea.comia.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.universidadeuropea.comia.entity.UserIngredient;
import com.universidadeuropea.comia.entity.UserIngredientId;

import jakarta.transaction.Transactional;


@Repository
public interface UserIngredientRepository extends CrudRepository<UserIngredient, UserIngredientId> {


    Iterable<UserIngredient> findByUsuarioId(Long userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM UserIngredient ui WHERE ui.usuario.id = :userId")
    void deleteByUsuarioId(Long userId);
    
}
