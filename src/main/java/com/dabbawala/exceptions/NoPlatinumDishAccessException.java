package com.dabbawala.exceptions;

public class NoPlatinumDishAccessException extends RuntimeException{
    @Override
    public String toString() {
        return "NoPlatinumDishAccess";
    }
}
