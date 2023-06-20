package com.dabbawala.models;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CartInformation {
    private Integer restaurantId;
    private Integer customerId;
    private List<RecepiesModel> recepiesList;
    private String deliveryLocation;
    private LocalDate date ;
}
