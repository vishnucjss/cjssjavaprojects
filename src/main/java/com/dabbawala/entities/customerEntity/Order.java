package com.dabbawala.entities.customerEntity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name="orders")
public class Order
{
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private List<OrderedRecipe> recepieList;
    private LocalDate OrderedDate;
    private  Double totalPrice ;
    private  String HotelName;
    private String HotelLocation;
    private  String deliveredLocation;






}
