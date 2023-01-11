package com.a2zshop.microservices.productinfoservice.exception;

import com.a2zshop.microservices.productinfoservice.model.SubCategory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends NotFoundException {

    private static final String message="No Product found with id = ";

    private static final String message_for_subCategory = "No Products available under sub-category : ";
    public ProductNotFoundException(long id) {
        super(message+id);
    }

    public ProductNotFoundException(SubCategory subCategory) {
        super(message_for_subCategory+subCategory.getSubCategoryName());
    }
}
