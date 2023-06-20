package com.dabbawala.repositories;


import com.dabbawala.entities.customerEntity.MyCart;
import com.dabbawala.entities.customerEntity.MyCartRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MyCartRepository extends JpaRepository<MyCart,Integer> {
}
