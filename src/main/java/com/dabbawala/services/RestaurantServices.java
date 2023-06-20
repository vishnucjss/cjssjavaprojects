package com.dabbawala.services;

import com.dabbawala.entities.restaurantEntity.Recipe;
import com.dabbawala.exceptions.RecipesNotFound;
import com.dabbawala.exceptions.RestaurantDoesNotExitException;
import com.dabbawala.models.RecipesDetails;
import com.dabbawala.repositories.RestaurantRepository;
import com.dabbawala.entities.restaurantEntity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantServices
{
    @Autowired
    RestaurantRepository restaurantRepository;

    public Restaurant addRestaurant(Restaurant restaurant)
    {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant addRecipesToRestaurants(List<Recipe> recipes,Integer id )
    {
        Optional<Restaurant> restaurantOptionalObject = restaurantRepository.findById(id);
        if(!restaurantOptionalObject.isPresent())
        {
            throw new RestaurantDoesNotExitException();
        }
        Restaurant restaurant=restaurantOptionalObject.get();
            List<Recipe> recipes1= restaurant.getRecepieList();
                  recipes1.addAll(recipes);
                  restaurant.setRecepieList(recipes1);

          return restaurantRepository.save(restaurant);
    }
    public List<Restaurant> getRestaurantByLocation( String location)
    {
        List<Restaurant> restaurants= (List<Restaurant>) restaurantRepository.findAll();
        List<Restaurant> resultRestaurants= restaurants.stream().
                filter(restaurant ->restaurant.getRecepieList().stream().
                        filter(recipe -> recipe.getService_location().stream().
                                filter(location1->location1.getName().equals(location)).findFirst().isPresent()
                        ).findFirst().isPresent())
                .collect(Collectors.toList());

        return resultRestaurants ;
    }
    public List<RecipesDetails> getRecipeBasedOnLocationAndOrderByPrice(String recipe1, String location)
    {

//        //restaurants based on recipe and location
//        List<Restaurant> restaurants= (List<Restaurant>) restaurantRepository.findAll();
//        List<Restaurant> resultRestaurants= restaurants.stream().
//                filter(restaurant ->restaurant.getRecepieList().stream().filter(recipe->recipe.getRecipeName().equals(recipe1)).
//                        filter(recipe -> recipe.getService_location().stream().
//                                filter(location1->location1.getName().equals(location)).findFirst().isPresent()
//                        ).findFirst().isPresent()).collect(Collectors.toList());
//        // recipe details
//        List<RecipeDetails> recipeDetailsList= resultRestaurants.stream().map(restaurant -> {
//            RecipeDetails recipeDetails= new RecipeDetails();
//            recipeDetails.setRestaurantName(restaurant.getName());
//            recipeDetails.setRecipeName(recipe1);
//            recipeDetails.setPrice(restaurant.getRecepieList().stream().filter(recipe ->recipe.getRecipeName().equals(recipe1)).
//                    map(recipe->recipe.getPrice()).findAny().get());return recipeDetails;}).collect(Collectors.toList());
//
//        //sorted by price
//        List<RecipeDetails> recipeDetailsList1=recipeDetailsList.stream().
//                sorted(Comparator.comparing(RecipeDetails::getPrice)).collect(Collectors.toList());
//        return recipeDetailsList1;

      List <RecipesDetails> recipesDetails=restaurantRepository.getRecipeDetails(recipe1,location);

      if(recipesDetails.isEmpty())
         throw new RecipesNotFound();
      else
          return  recipesDetails;

    }


}
