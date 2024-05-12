package com.universidadeuropea.comia.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.universidadeuropea.comia.entity.Profiles;
import java.util.Optional;


@RepositoryRestResource(path = "profiles")
public interface ProfilesRepository extends CrudRepository<Profiles, Long> {

    Optional<Profiles> findByUserId(Long userId);
    @SuppressWarnings("unchecked")
    Profiles save(Profiles profile);
}
