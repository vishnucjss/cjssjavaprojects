package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String salary;
    private String department;
    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name="address_id", referencedColumnName ="id")
private Address address;


}
