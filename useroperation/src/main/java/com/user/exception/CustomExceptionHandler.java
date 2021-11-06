package com.user.exception;


import com.user.common.Utils;
import com.user.model.ErrorMessage;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private Utils utils;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException arguments, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        log.info("hello world");
        BindingResult bindingResult = arguments.getBindingResult();
        List<String> validationMessages = new ArrayList<>();
        List<ObjectError> objectErrors = bindingResult.getAllErrors();
        for (ObjectError objectError : objectErrors) {
            String defaultMessage = objectError.getDefaultMessage();
            validationMessages.add(defaultMessage);
        }
        return new ResponseEntity<>(validationMessages, HttpStatus.BAD_REQUEST);
    }


    private List<String> addErrorMessages(List<FieldError> fieldErrors) {
        List<String> validationMessages = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            log.info("#### field Name  " + fieldError.getField());
            log.info("#### Rejected Value Name  " + fieldError.getRejectedValue());
            log.info("Default Message in field error " + fieldError.getDefaultMessage());
            validationMessages.add(fieldError.getDefaultMessage());
        }
        return validationMessages;
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleCustomException(Exception ex, WebRequest request) throws Exception {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), utils.currentDateTime());
        log.info("Clsss name " + ex.getClass().getName());
        log.info("type name " + ex.getClass().getTypeName());

        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidInputException.class)
    public final ResponseEntity<Object> invalidInputException(InvalidInputException ex, WebRequest request) throws Exception {
        ErrorMessage errorMessage = null;
        if (ex.getErrorList() != null && ex.getErrorList().size() > 0) {
            errorMessage = new ErrorMessage(StringUtils.join(ex.getErrorList(), "\n"), utils.currentDateTime());
        } else {
            errorMessage = new ErrorMessage(ex.getMessage(), utils.currentDateTime());
        }
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<Object> resourceNotFound(ResourceNotFoundException ex, WebRequest request) throws Exception {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), utils.currentDateTime());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public final ResponseEntity<Object> dataIntegrity(DataIntegrityViolationException ex, WebRequest request) throws Exception {
        ErrorMessage errorMessage = new ErrorMessage("The generated code already exist", utils.currentDateTime());
        System.out.println("Error message " + errorMessage.getErrorMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);

    }

    @ExceptionHandler(ExpiredJwtException.class)
    public final ResponseEntity<Object> tokenExpired(ExpiredJwtException ex, WebRequest webRequest) {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), utils.currentDateTime());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateResourceFoundException.class)
    public final ResponseEntity<Object> invalidToken(DuplicateResourceFoundException ex, WebRequest webRequest) {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), utils.currentDateTime());
        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }

    /* @ExceptionHandler(ValidationException.class)
     public final ResponseEntity<Object> validationException(ValidationException ex, WebRequest request) throws Exception {
         BindingResult bindingResult = ex.getBindingResult();
         List<String> validationMessages = new ArrayList<>();
         List<ObjectError> objectErrors = bindingResult.getAllErrors();
         bindingResult.getFieldErrors();
         List<FieldError> fr = bindingResult.getFieldErrors();
         for (FieldError f : fr) {
             System.out.println(f.getField());
             System.out.println(f.getRejectedValue());
         }
         for (ObjectError objectError : objectErrors) {
             objectError.getObjectName();
             String defaultMessage = objectError.getDefaultMessage();
             validationMessages.add(defaultMessage + " Test");
         }
         return new ResponseEntity<>(validationMessages, HttpStatus.BAD_REQUEST);

     }
 */
    private LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }
}
