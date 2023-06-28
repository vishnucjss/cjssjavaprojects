package com.dabbawala.services;

import org.springframework.stereotype.Service;




import com.dabbawala.entities.customerEntity.*;
import com.dabbawala.entities.restaurantEntity.Restaurant;
import com.dabbawala.exceptions.*;
import com.dabbawala.exceptions.DuplicateCustomersNotAllowed;
import com.dabbawala.repositories.CustomerRepository;
import com.dabbawala.repositories.MyCartRecepiesRepository;
import com.dabbawala.repositories.MyCartRepository;
import com.dabbawala.repositories.RestaurantRepository;
import com.dabbawala.entities.customerEntity.Customer;
import com.dabbawala.entities.customerEntity.Order;
import com.dabbawala.models.CartInformation;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    MyCartRecepiesRepository myCartRecepiesRepository;

    @Autowired
    MyCartRepository myCartRepository;

    public Customer addCustomer(Customer customer)
    {


        List<Customer> customers=customerRepository.findAll();
       for(Customer customer1:customers)
       {
           if(customer1.equals(customer))
               throw new DuplicateCustomersNotAllowed();

       }
           System.out.println(customer.equals(customer));

        return customerRepository.save(customer);
    }

    public Customer addRecipesToCart( CartInformation cartInformation)
    {

        Optional<Customer>  customerOptionalObject  = customerRepository.findById(cartInformation.getCustomerId());
        Optional<Restaurant> restaurantOptionalObject = restaurantRepository.findById(cartInformation.getRestaurantId());
        if(!customerOptionalObject.isPresent())
        {
            throw new CustomerDoesNotExitException();
        }
        else if(!restaurantOptionalObject.isPresent())
        {
            throw new RestaurantDoesNotExitException();
        }
        Restaurant restaurant=restaurantOptionalObject.get();
        Customer customer=customerOptionalObject.get();


        MyCart myCart=new MyCart();
        List<MyCartRecipe> myCartRecipes=new ArrayList<>();

        //inserting data to myCart
        myCart.setRestaurantName(restaurant.getName());
        myCart.setRestaurantLocation(restaurant.getLocation());
        myCart.setDeliveryLocation(cartInformation.getDeliveryLocation());

        //inserting data to myCartRecipes;
        restaurant.getRecepieList().
                forEach( recipe -> {
                    if(recipe.getDishType().equals("platinum")&&customer.getMembership().equals("normal"))
                        throw new NoPlatinumDishAccessException();

                    cartInformation.getRecepiesList().stream().
                            filter(recipe1-> recipe.getRecipeName().equals(recipe1.getRecipeName())&&(
                                    (recipe.getDishType().equals("normal"))||(customer.getMembership().equals("platinum")&&
                                            recipe.getDishType().equals("platinum")))
                            ).
                            forEach(recepie1-> {


                                MyCartRecipe myCartRecipe= new MyCartRecipe();
                                myCartRecipe.setRecipeName(recepie1.getRecipeName());
                                myCartRecipe.setPrice(recipe.getPrice());
                                myCartRecipe.setQuantity(recepie1.getQuantity());
                                myCartRecipe.setTotal_Price(myCartRecipe.getPrice()*myCartRecipe.getQuantity());
                                myCartRecipes.add(myCartRecipe);
                            });
                });
        double  totalPrice =myCartRecipes.stream().mapToDouble(recipe-> recipe.getTotal_Price()).sum();

        myCart.setRecipes(myCartRecipes);
        myCart.setOrderDate(cartInformation.getDate());
        myCart.setBeforeDiscount(totalPrice);

        if(customer.getMembership().equals("platinum"))
        {
            myCart.setMembershipDiscount(totalPrice*10/100);
        }

        myCart.setTotalBill(myCart.getBeforeDiscount()- myCart.getMembershipDiscount());
        myCart.setCustomer(customer);


        //inserting myCart data to customer
        customer.setMyCart(myCart);

        customerRepository.save(customer);
        myCartRepository.deleteById(customer.getMyCart().getId());
        return customer;
    }
    public Customer placeOrder( Integer id)
    {

        // Customer data based on the id
        Customer customer=customerRepository.findById(id).get();
        MyCart myCart=customer.getMyCart();
        if(myCart==null)
            throw new CartIsEmptyException();
        List<MyCartRecipe> myCartRecipes =myCart.getRecipes();

        List<Order> orders=new ArrayList<>();
        Order order=new Order();
        List<OrderedRecipe> orderedRecipe=new ArrayList<>();
        //inserting data to the  order object
        order.setOrderedDate(myCart.getOrderDate());
        order.setHotelName(myCart.getRestaurantName());
        order.setHotelLocation(myCart.getRestaurantLocation());
        order.setTotalPrice(myCart.getTotalBill());


        //inserting the recipes to orderedRecipes
        myCartRecipes.forEach(recepie1->
                {
                    System.out.println(recepie1);
                    OrderedRecipe recepie=new OrderedRecipe();
                    recepie.setRecipe(recepie1.getRecipeName());
                    recepie.setQuantity(recepie1.getQuantity());
                    recepie.setPrice(recepie1.getPrice());
                    orderedRecipe.add(recepie);

                }
        );

        order.setDeliveredLocation(myCart.getDeliveryLocation());
        order.setRecepieList(orderedRecipe);
        orders.add(order);
        List<Order> orders1=customer.getOrders();
        orders1.addAll(orders);
        customer.setOrders(orders1);

        //removing the myCart data
        //saving orders data in to respected customer

        customerRepository.save(customer);
        myCartRepository.deleteById(myCart.getId());


        return customer;

    }
    public List<Order> getAllOrdersByDate(String  date1,Integer id)
    {
        LocalDate date = LocalDate.parse(date1);
        Optional<Customer>  optionalObject = customerRepository.findById(id);
        if(!optionalObject.isPresent() )
            throw new CustomerDoesNotExitException();
        Customer customer=optionalObject.get();
        List<Order> orders= customer.getOrders();
        if (orders==null || orders.isEmpty())
            throw new OrdersDoesNotExitException();

        //getting orders by date
        List<Order> ResultOrders=orders.stream().filter(order -> order.getOrderedDate().isEqual(date)).collect(Collectors.toList());

        return ResultOrders;
    }



}
