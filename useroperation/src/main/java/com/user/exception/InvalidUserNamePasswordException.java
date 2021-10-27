package com.user.exception;

import java.util.List;

public class InvalidUserNamePasswordException extends RuntimeException {

    private List<String> errorList;
    public InvalidUserNamePasswordException(String message) {
        super(message);
    }

    public InvalidUserNamePasswordException(List<String> errorList) {
        this.errorList = errorList;
    }

    public List<String> getErrorList() {
        return errorList;
    }
}

