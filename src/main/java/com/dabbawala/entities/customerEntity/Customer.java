package com.dabbawala.entities.customerEntity;

import com.dabbawala.validations.ValidateAge;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;
import java.util.Objects;
@Data
@Entity
public class Customer
{
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    @NotNull
    @Pattern(regexp ="[\\s.\\w]{0,30}",message ="Enter valid and name characters must be less than 25 ")
    private String customerName;
    @NotEmpty
    @NotNull
    @Pattern(regexp ="[a-z A-z 0-9 .]+[@]{1}[a-z A-z]+[.]{1}+[a-z]+",message="Enter valid email")
    private  String email;
    @NotEmpty
    @NotNull
    @Pattern(regexp = "[0-9]{10}",message="digits must be 10")
    private String phoneNo;
    @NotEmpty
    @NotNull
    @Pattern(regexp ="[a-z,A-Z]+" )
    private String location;
    @ValidateAge
    private String dateOfBirth;
    @NotEmpty
    @NotNull
    @Pattern(regexp="platinum|normal",message ="enter only platinum or normal")
    private String membership;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer-id")
    private List<Order> orders;
    @OneToOne(cascade=CascadeType.ALL,mappedBy ="customer")
  @JsonManagedReference
    private MyCart myCart;






}
