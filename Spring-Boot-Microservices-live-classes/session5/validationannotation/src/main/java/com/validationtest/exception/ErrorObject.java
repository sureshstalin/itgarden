package com.validationtest.exception;

import java.time.LocalDateTime;

public class ErrorObject {

    private int errorCode;
    private String errorMessage;
    private LocalDateTime localDateTime;

//    public ErrorObject() {
//    }

    public ErrorObject(int errorCode, String errorMessage, LocalDateTime localDateTime) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.localDateTime = localDateTime;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
