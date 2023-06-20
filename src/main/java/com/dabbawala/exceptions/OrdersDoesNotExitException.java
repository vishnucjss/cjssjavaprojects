package com.dabbawala.exceptions;

public class OrdersDoesNotExitException extends RuntimeException{
    @Override
    public String toString() {
        return "OrdersDoesNotExitException{}";
    }
}
