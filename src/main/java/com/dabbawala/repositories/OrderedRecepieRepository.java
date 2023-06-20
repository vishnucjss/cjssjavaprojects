package com.dabbawala.repositories;

import com.dabbawala.entities.customerEntity.OrderedRecipe;
import org.springframework.data.repository.CrudRepository;

public interface OrderedRecepieRepository extends CrudRepository<OrderedRecipe,Integer> {
}
