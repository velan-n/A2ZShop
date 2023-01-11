package com.a2zshop.microservices.productinfoservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoryNotFoundException extends NotFoundException {

    private static final String   message = "No category available with";

    public CategoryNotFoundException(String name){
        super(String.format("%s name = %s",message,name));
    }

    public CategoryNotFoundException(long id){
        super(String.format("%s id = %d",message,id));
    }
}
