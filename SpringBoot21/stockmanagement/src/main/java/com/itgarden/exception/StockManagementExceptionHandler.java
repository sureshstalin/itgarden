package com.itgarden.exception;

import com.itgarden.util.CommonUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@ControllerAdvice
@RestController
public class StockManagementExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> validationMessages = new ArrayList<>();
        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        for (ObjectError objectError : objectErrors) {
            String defaultMessage = objectError.getDefaultMessage();
            validationMessages.add(defaultMessage);
        }
        ValidationMessage validationMessage = new ValidationMessage(validationMessages.toString(), 101, getCurrentDateTime());
        return new ResponseEntity<>(validationMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleCustomException(Exception ex, WebRequest request) throws Exception {

        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(),201,CommonUtils.getCurrentDateTime());
        return  new ResponseEntity<>(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidInputException.class)
    public final ResponseEntity<Object> invalidInputException(InvalidInputException ex, WebRequest request) throws Exception {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(),202,CommonUtils.getCurrentDateTime());
        return  new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<Object> resourceNotFund(ResourceNotFoundException ex, WebRequest request) throws Exception {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(),203,CommonUtils.getCurrentDateTime());
        return  new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
    }

    private Timestamp getCurrentDateTime() {
        Timestamp timestamp = new Timestamp(Calendar.getInstance().getTimeInMillis());
        return timestamp;
    }
}
