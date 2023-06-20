package com.dabbawala.repositories;

import com.dabbawala.entities.restaurantEntity.Restaurant;
import com.dabbawala.models.RecipeDetails;
import com.dabbawala.models.RecipesDetails;
import com.dabbawala.models.details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {
  @Query(value = "select  new com.dabbawala.models.RecipesDetails(R.name,Rcpe.recipeName,Rcpe.price)"
          +"from Restaurant R join R.recepieList Rcpe join Rcpe.service_location SerLoc " +
          "where Rcpe.recipeName=?1 And SerLoc.name=?2 order by Rcpe.price")
  public List<RecipesDetails> getRecipeDetails(String recipeName,String location);

}
