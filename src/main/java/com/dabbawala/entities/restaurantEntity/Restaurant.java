package com.dabbawala.entities.restaurantEntity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {

        Restaurant that = (Restaurant) o;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getLocation(), that.getLocation());
    }


}
