package com.a2zshop.microservices.productinfoservice.exceptionhandler;

import com.a2zshop.microservices.productinfoservice.exception.CustomFeignException;
import com.a2zshop.microservices.productinfoservice.exception.DuplicateEntryException;
import com.a2zshop.microservices.productinfoservice.exception.NotFoundException;
import com.a2zshop.microservices.productinfoservice.exception.ProductNotFoundException;
import feign.FeignException;
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
import java.util.List;

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
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleNotFoundExceptions(Exception ex, WebRequest request){
        messages=new ArrayList<>();
        messages.add(ex.getMessage());
        ErrorDetails errorDetails=new ErrorDetails(LocalDateTime.now(),messages,request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateEntryException.class)
    public final ResponseEntity<ErrorDetails> handleDuplicateEntryException(Exception ex, WebRequest request){
        messages=new ArrayList<>();
        messages.add(ex.getMessage());
        ErrorDetails errorDetails=new ErrorDetails(LocalDateTime.now(),messages,request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CustomFeignException.class)
    public final ResponseEntity<ErrorDetails> handleFeignException(CustomFeignException ex, WebRequest request){
        return new ResponseEntity<>(ex.getErrorDetails(), HttpStatus.valueOf(ex.getStatus()));
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        messages=new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(
                fieldError -> {
                    String fieldName=fieldError.getField();
                    String formattedErrorMessage=formatErrorMessage(fieldName,fieldError.getDefaultMessage());
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

}
