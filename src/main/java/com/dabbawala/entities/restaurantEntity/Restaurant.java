package com.dabbawala.entities.restaurantEntity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Restaurant {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private   String location;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="restaurant_id")
    private List<Recipe> recepieList;

}
