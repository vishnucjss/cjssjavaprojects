package com.dabbawala.repositories;

import com.dabbawala.entities.restaurantEntity.serviceLocation;
import org.springframework.data.repository.CrudRepository;

public interface ServiceLocationRepository extends CrudRepository<serviceLocation,Integer> {
}
