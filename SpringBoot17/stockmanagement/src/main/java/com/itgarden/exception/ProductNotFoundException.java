package com.itgarden.exception;

public class ProductNotFoundException extends Exception{

    public ProductNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
