package com.universidadeuropea.comia.model;

import java.util.List;
import lombok.Data;

@Data
public class Ingredient {
    private int id;
    private String aisle;
    private String image;
    private String consistency;
    private String name;
    private String nameClean;
    private String original;
    private String originalName;
    private double amount;
    private String unit;
    private List<String> meta;
    private Measures measures;
}
