package com.universidadeuropea.comia.model;

import lombok.Data;

@Data
public class Measures {
    private Measure us;
    private Measure metric;


    @Data
    public static class Measure {
        private double amount;
        private String unitShort;
        private String unitLong;

    }
}
