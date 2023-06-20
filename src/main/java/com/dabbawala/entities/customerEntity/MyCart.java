package com.dabbawala.entities.customerEntity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Data
public class MyCart {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;
    private String restaurantName;
    private String restaurantLocation;
    private  String deliveryLocation;
    private LocalDate orderDate;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="myCart-id")
    private List<MyCartRecipe> recipes;
    private   double  beforeDiscount ;
    private   double membershipDiscount;
    private Double totalBill;
    @OneToOne()
    @JoinColumn(name = "customer_id")
  @JsonBackReference
    private Customer customer;


}
