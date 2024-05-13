package com.universidadeuropea.comia.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.universidadeuropea.comia.entity.Usuario;
import java.util.Optional;


@RepositoryRestResource(path = "usuarios")
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

}
