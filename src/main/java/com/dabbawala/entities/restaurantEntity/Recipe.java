package com.dabbawala.entities.restaurantEntity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data
public class Recipe
{
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
   private  Integer id;
   private   String recipeName;
    private String  type;
    private double  price ;
    private String dishType;
    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name="recipe_id")
    private List<serviceLocation> service_location;


}
