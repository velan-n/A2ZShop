package com.a2zshop.microservices.productinfoservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateEntryException extends Exception {

    private static final String message="already exists";

    public DuplicateEntryException(String className,String name) {
        super(String.format("%s %s %s",className,name,message));
    }
}
