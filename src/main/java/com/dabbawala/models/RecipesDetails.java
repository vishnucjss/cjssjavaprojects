package com.dabbawala.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class RecipesDetails {
    private String restaurantName;
    private String  recipeName;
    private double price ;

}
