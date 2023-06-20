package com.dabbawala.controller;


import com.dabbawala.entities.restaurantEntity.Recipe;
import com.dabbawala.entities.restaurantEntity.Restaurant;
import com.dabbawala.models.*;
import com.dabbawala.repositories.MyCartRecepiesRepository;
import com.dabbawala.repositories.MyCartRepository;
import com.dabbawala.repositories.RestaurantRepository;
import com.dabbawala.services.CustomerService;
import com.dabbawala.services.RestaurantServices;
import com.dabbawala.entities.customerEntity.Customer;
import com.dabbawala.entities.customerEntity.Order;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyController {
    @Autowired
    RestaurantServices restaurantServices ;
    @Autowired
    CustomerService customerService;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    MyCartRepository myCartRepository;

@Autowired
    MyCartRecepiesRepository myCartRecepiesRepository;
    @PostMapping("/addCustomers")
    public Customer addCustomer(@Valid @RequestBody Customer customer)

    {

        return customerService.addCustomer(customer);
    }

    @PostMapping("/addRestaurant")
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant)
    {
        return  restaurantServices.addRestaurant(restaurant);
    }
    @PostMapping("/addRecipesToRestaurants/{id}")
    public Restaurant addRecipesToRestaurants(@RequestBody List<Recipe> recipes,@PathVariable Integer id)
    {
        return restaurantServices.addRecipesToRestaurants(recipes,id) ;
    }

    @GetMapping("/getRestaurantsByLocation/{location}")
     public List<Restaurant> getRestaurantByLocation(@PathVariable  String location)
    {
        return restaurantServices.getRestaurantByLocation(location) ;
    }

    @GetMapping("/getRecipeBasedOnLocationAndOrderByPrice/{recipe1}/{location}")
    public List<RecipesDetails>getRecipeBasedOnLocation(@PathVariable String recipe1,@PathVariable String location)
    {
        return restaurantServices.getRecipeBasedOnLocationAndOrderByPrice(recipe1,location);
    }

    @PostMapping("/addRecipesToCart")
    public Customer addRecipesToCart(@RequestBody CartInformation cartInformation)
    {
        return customerService.addRecipesToCart(cartInformation);
    }

    @PostMapping("/placeOrder/{id}")
    public Customer placeOrder(@PathVariable Integer id)
    {
         return customerService.placeOrder(id);
    }

    @GetMapping("/getAllOrdersByDate/{date1}/{id}")
    public List<Order> getAllOrdersByDate(@PathVariable String  date1, @PathVariable Integer id)
    {

         return customerService.getAllOrdersByDate(date1,id);
    }

    @GetMapping ("/delete")
    public void deleteMyCart()
    {
         myCartRecepiesRepository.deleteById(4);

    }










}