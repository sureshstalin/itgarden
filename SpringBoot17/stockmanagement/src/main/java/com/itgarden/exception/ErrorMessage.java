package com.itgarden.exception;

import java.sql.Timestamp;

public class ErrorMessage {

    private String errorMessage;
    private int errorCode;
    private Timestamp timestamp;

    public ErrorMessage(String errorMessage, int errorCode, Timestamp timestamp) {
        this.errorMessage = errorMessage;
        this.errorCode=errorCode;
        this.timestamp=timestamp;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
