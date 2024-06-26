package com.universidadeuropea.comia.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, name = "first_name")
    private String firstName;

    @Column(nullable = true, name = "last_name")
    private String lastName;

    @Column(nullable = false, name = "password")
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = true)
    private Boolean enabled;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @Column(name = "fecha_baja")
    private Date fechaBaja;

    @OneToMany(mappedBy = "usuario")
    private Set<UserIngredient> userIngredients = new HashSet<>();

    @OneToMany(mappedBy = "usuario")
    private Set<UserFavoritos> userFavoritos = new HashSet<>();

}
