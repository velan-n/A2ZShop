package com.a2zshop.microservices.productinfoservice.exception;

import com.a2zshop.microservices.productinfoservice.exceptionhandler.ErrorDetails;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomFeignException extends RuntimeException{

    private ErrorDetails errorDetails;
    private int status;
}
