package com.a2zshop.microservices.userinfoservice.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class ErrorDetails {
    private LocalDateTime timeStamp;
    private List<String> messages;
    private String details;
}
