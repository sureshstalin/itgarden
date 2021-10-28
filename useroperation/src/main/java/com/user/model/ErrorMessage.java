package com.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;



//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class ErrorMessage {

    private String errorMessage;
    private LocalDateTime localDateTime;

    public ErrorMessage(String errorMessage,LocalDateTime localDateTime) {
        this.errorMessage=errorMessage;
        this.localDateTime=localDateTime;
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
