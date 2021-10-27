package com.user.exception;

public class DuplicateResourceFoundException extends Exception {

    public DuplicateResourceFoundException(String errorMessage) {
        super(errorMessage);
    }
}
