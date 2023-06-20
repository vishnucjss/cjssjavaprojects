package com.dabbawala.exceptions;

public class CartIsEmptyException extends  RuntimeException{
    @Override
    public String toString() {
        return "CartIsEmpty{}";
    }

}
