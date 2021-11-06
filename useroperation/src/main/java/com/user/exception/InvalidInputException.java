package com.user.exception;

import java.util.List;

public class InvalidInputException extends RuntimeException {

    private List<String> errorList;

    public InvalidInputException(List<String> errorList) {
        this.errorList = errorList;
    }

    public List<String> getErrorList() {
        return errorList;
    }
}

