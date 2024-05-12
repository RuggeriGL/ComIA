package com.universidadeuropea.comia.entity;

import java.util.Date;

import com.universidadeuropea.comia.utils.JsonUtil;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Profiles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id")
    private Long userId; 

    @Column(name = "intolerances")
    private String intolerances; 

    @Column(name = "diets")
    private String diets; 

    public void setIntolerances(String[] intolerances) {
        this.intolerances = JsonUtil.toJsonString(intolerances);
    }

    public String[] getIntolerances() {
        return JsonUtil.fromJsonString(intolerances);
    }

    // Setter y getter para diets
    public void setDiets(String[] diets) {
        this.diets = JsonUtil.toJsonString(diets);
    }

    public String[] getDiets() {
        return JsonUtil.fromJsonString(diets);
    }

}
