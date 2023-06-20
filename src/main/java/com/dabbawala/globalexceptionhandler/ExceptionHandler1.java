package com.dabbawala.globalexceptionhandler;

import com.dabbawala.exceptions.*;
import com.dabbawala.models.DuplicateCustomersNotAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.DateTimeException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler1 {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
    @ExceptionHandler(DateTimeException.class)
    public String  ageException()
    {
        return "enter age correctly";
    }

    @ExceptionHandler( NoPlatinumDishAccessException.class)
    public String  noPlatinumDishAccessException()
    {
        return "normal type membership users cannot order platinum dishes";
    }
    @ExceptionHandler( CartIsEmptyException.class)
    public String  cartIsEmpty()
    {
        return "Cart is empty";
    }
    @ExceptionHandler(OrdersDoesNotExitException.class)
    public String  ordersDoesNotExit()
    {
        return "OrdersDoesNotExit";
    }
    @ExceptionHandler(CustomerDoesNotExitException.class)
    public String  customerDoesNotExit()
    {
        return "CustomerDoesNotExit";
    }
    @ExceptionHandler(RestaurantDoesNotExitException.class)
    public String  restaurantDoesNotExit()
    {
        return "RestaurantDoesNotExit";
    }

    @ExceptionHandler(RecipesNotFound.class)
    public String  recipesNotFound()
    {
        return "RecipesNotFound";
    }
    @ExceptionHandler(DuplicateCustomersNotAllowed.class)
    public String  RecipesNotFound()
    {
        return "DuplicateCustomersNotAllowed";
    }



}
