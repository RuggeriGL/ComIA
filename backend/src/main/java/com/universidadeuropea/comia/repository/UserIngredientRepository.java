package com.universidadeuropea.comia.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.universidadeuropea.comia.entity.UserIngredient;
import com.universidadeuropea.comia.entity.VfridgeIngredient;

import jakarta.transaction.Transactional;


@Repository
public interface UserIngredientRepository extends CrudRepository<UserIngredient, Long> {


    List<UserIngredient> findByIngredientId(Long ingredientId);

    @SuppressWarnings({ "null", "unchecked" })
    UserIngredient save(UserIngredient userIngredient);

    @Query(nativeQuery = true, name = "UserIngredient.findIngredientsByUserId")
    VfridgeIngredient[] findByUserId(@Param("userId") Long userId);
    

    @Modifying
    @Transactional
    @Query("DELETE FROM UserIngredient u WHERE u.userId = :userId")
    void deleteByUserId(Long userId);
    
}
