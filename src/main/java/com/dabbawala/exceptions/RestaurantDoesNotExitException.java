package com.dabbawala.exceptions;

public class RestaurantDoesNotExitException extends  RuntimeException{
    @Override
    public String toString() {
        return "RestaurantDoesNotExit{}";
    }
}
