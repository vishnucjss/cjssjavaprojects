package com.dabbawala.entities.restaurantEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class serviceLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer  id;
    String name;
}
