package com.itgarden.exception;

public class ResourceNotFoundException extends Exception{

    public ResourceNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
