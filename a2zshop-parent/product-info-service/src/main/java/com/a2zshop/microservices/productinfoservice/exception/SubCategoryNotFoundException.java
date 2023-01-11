package com.a2zshop.microservices.productinfoservice.exception;

import com.a2zshop.microservices.productinfoservice.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SubCategoryNotFoundException extends NotFoundException {

    private static final String message_for_name = "No sub-category available with name : ";
    private static final String message_for_category = "No sub-categories available under category : ";
    private static final String message_for_id = "No sub-category available with id : ";

    public SubCategoryNotFoundException(String name){
        super(message_for_name +name);
    }

    public SubCategoryNotFoundException(Category category){
        super(message_for_category +category.getCategoryName());
    }

    public SubCategoryNotFoundException(long id){
        super(message_for_id +id);
    }
}
