package com.dabbawala.repositories;

import com.dabbawala.entities.restaurantEntity.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe,Integer>
{

}
