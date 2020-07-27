package com.itgarden.exception;

import java.sql.Timestamp;

public class ValidationMessage {

    private String validationMessage;
    private int validationCode;
    private Timestamp timestamp;

    public ValidationMessage(String validationMessage,int validationCode,Timestamp timestamp) {
        this.validationMessage = validationMessage;
        this.validationCode=validationCode;
        this.timestamp=timestamp;
    }

    public String getValidationMessage() {
        return validationMessage;
    }

    public void setValidationMessage(String validationMessage) {
        this.validationMessage = validationMessage;
    }

    public int getValidationCode() {
        return validationCode;
    }

    public void setValidationCode(int validationCode) {
        this.validationCode = validationCode;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
