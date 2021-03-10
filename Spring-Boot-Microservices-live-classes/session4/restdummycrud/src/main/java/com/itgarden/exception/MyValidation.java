package com.itgarden.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class MyValidation extends ResponseEntityExceptionHandler  {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException arguments, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> validationMessages = new ArrayList<>();

        BindingResult bindingResult = arguments.getBindingResult();
        List<ObjectError> objectErrors = bindingResult.getAllErrors();
        for (ObjectError objectError : objectErrors) {
            String defaultMessage = objectError.getDefaultMessage();
            validationMessages.add(defaultMessage);
        }
        return new ResponseEntity<>(validationMessages, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<Object> resourceNotFound(ResourceNotFoundException ex, WebRequest request) throws Exception {
//        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), Utils.currentDateTime());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
