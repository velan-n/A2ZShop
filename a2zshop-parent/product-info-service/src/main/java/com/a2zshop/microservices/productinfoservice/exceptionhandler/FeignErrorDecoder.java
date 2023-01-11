package com.a2zshop.microservices.productinfoservice.exceptionhandler;

import com.a2zshop.microservices.productinfoservice.exception.CustomFeignException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.io.InputStream;

public class FeignErrorDecoder implements ErrorDecoder {

    private ErrorDetails errorDetails;

    @Override
    public Exception decode(String s, Response response) {
        try (InputStream body=response.body().asInputStream()){
            ObjectMapper objectMapper=new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule()); //needed dependecy = jackson-datatype-jsr310 to map LocalDateTime
            errorDetails =objectMapper.readValue(body,ErrorDetails.class);
        }
        catch (IOException ex){
            return new Exception(ex.getMessage());
        }
        return new CustomFeignException(errorDetails,response.status());
    }
}
