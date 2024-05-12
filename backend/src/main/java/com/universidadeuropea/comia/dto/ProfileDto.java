package com.universidadeuropea.comia.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class ProfileDto {
    private String firstName;
    private String lastName;
    private Long id;
    private Long userId;
    private String[] intolerances;
    private String[] diets;

    // Constructor principal
    public ProfileDto(String firstName, String lastName, Long id, Long userId, String[] intolerances, String[] diets) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.userId = userId;
        this.intolerances = intolerances;
        this.diets = diets;
    }

    // Constructor alternativo que debe llamar al principal
    public ProfileDto(String firstName, String lastName) {
        this(firstName, lastName, null, null, null, null);
    }
}

