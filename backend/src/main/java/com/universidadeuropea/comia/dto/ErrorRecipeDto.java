package com.universidadeuropea.comia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorRecipeDto {

    private String status;
    private String code;
    private String message;

}
