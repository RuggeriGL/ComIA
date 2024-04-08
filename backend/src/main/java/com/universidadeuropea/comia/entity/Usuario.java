package com.universidadeuropea.comia.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private Boolean enabled;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @Column(name = "fecha_baja")
    private Date fechaBaja;

    @Column
    private Character rol;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public Character getRol() {
        return rol;
    }

    public void setRol(Character rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
                + ", enabled=" + enabled + ", fechaCreacion=" + fechaCreacion + ", fechaBaja=" + fechaBaja + ", rol="
                + rol + "]";
    }
    
    


}
