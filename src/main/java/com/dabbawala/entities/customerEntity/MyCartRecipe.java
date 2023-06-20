package com.dabbawala.entities.customerEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Objects;

@Entity
@Data
public class MyCartRecipe
{@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String recipeName;
    private double quantity;
    private  double  price ;
    private  double total_Price;



}
