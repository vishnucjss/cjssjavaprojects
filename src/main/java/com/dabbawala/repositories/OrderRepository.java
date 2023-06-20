package com.dabbawala.repositories;

import com.dabbawala.entities.customerEntity.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order,Integer> {
}
