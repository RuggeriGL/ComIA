package com.universidadeuropea.comia.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_favoritos")
@Entity
public class UserFavoritos {
    @EmbeddedId
    private UserFavoritoId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    @ManyToOne
    @MapsId("favoritoId")
    @JoinColumn(name = "favorito_id")
    private Favoritos favorito;

}


