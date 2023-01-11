package com.a2zshop.microservices.ratingservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RatingNotFoundException extends Exception {
    private final static String message="No ratings available";

    public RatingNotFoundException() {
        super(message);
    }
}
