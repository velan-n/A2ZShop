package com.a2zshop.microservices.productinfoservice.dto;

import lombok.Data;

@Data
public class RatingDto {
    private UserDto user;
    private ProductDto product;
    private short ratingValue;
}
