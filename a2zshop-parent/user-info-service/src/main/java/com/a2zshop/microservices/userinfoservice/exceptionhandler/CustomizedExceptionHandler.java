package com.a2zshop.microservices.userinfoservice.exceptionhandler;

import com.a2zshop.microservices.userinfoservice.exception.UserNotFoundException;
import org.hibernate.DuplicateMappingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

    private List<String> messages;

    private String formatErrorMessage(String fieldName, String message) {
        String formattedFieldName=fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
        return String.format("%s %s",formattedFieldName, message);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request){
        messages=new ArrayList<>();
        messages.add(ex.getMessage());
        ErrorDetails errorDetails=new ErrorDetails(LocalDateTime.now(),messages,request.getDescription(false));
        return  new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleUserNotFoundExceptions(Exception ex, WebRequest request){
        messages=new ArrayList<>();
        messages.add(ex.getMessage());
        ErrorDetails errorDetails=new ErrorDetails(LocalDateTime.now(),messages,request.getDescription(false));
        return  new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        messages=new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(
                error -> {
                    String formattedErrorMessage=formatErrorMessage(error.getField(),error.getDefaultMessage());
                    messages.add(formattedErrorMessage);
                }
        );

        ErrorDetails errorDetails=new ErrorDetails(LocalDateTime.now(),messages,request.getDescription(false));
        return  new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        messages=new ArrayList<>();
        ex.getConstraintViolations().stream().forEach(
                constraintViolation -> {
                    final String[] fieldName = new String[1];
                    constraintViolation.getPropertyPath().spliterator().forEachRemaining(
                            node -> {
                                fieldName[0] =node.getName();
                            }
                    );
                    String formattedErrorMessage=formatErrorMessage(fieldName[0],constraintViolation.getMessage());
                    messages.add(formattedErrorMessage);

                }
        );

        ErrorDetails errorDetails=new ErrorDetails(LocalDateTime.now(),messages,request.getDescription(false));
        return  new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateMappingException.class)
    public final ResponseEntity<ErrorDetails> handleDuplicateMappingExceptions(DuplicateMappingException ex, WebRequest request){
        messages=new ArrayList<>();
        messages.add(ex.getMessage());
        ErrorDetails errorDetails=new ErrorDetails(LocalDateTime.now(),messages,request.getDescription(false));
        return  new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.CONFLICT);
    }
}
