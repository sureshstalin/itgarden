package com.validationtest.exception;

public class ResourceNotFoundException extends  Exception{

    public ResourceNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
