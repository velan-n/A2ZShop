package com.a2zshop.microservices.userinfoservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends Exception {

    private static final String message="No User found with id = ";
    public UserNotFoundException(long id) {
        super(message+id);
    }
}
