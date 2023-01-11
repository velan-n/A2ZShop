package com.a2zshop.microservices.userinfoservice.controller;

import com.a2zshop.microservices.userinfoservice.dto.request.UserRequestDTO;
import com.a2zshop.microservices.userinfoservice.dto.response.UserResponseDTO;
import com.a2zshop.microservices.userinfoservice.exception.UserNotFoundException;
import com.a2zshop.microservices.userinfoservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> saveUser(@Valid @RequestBody UserRequestDTO userRequestDto){
         return new ResponseEntity<>(userService.saveUser(userRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> retrieveAllUsers(){
        return ResponseEntity.ok(userService.retrieveAllUsers());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseDTO> retrieveUserById(@PathVariable("id") long userId) throws UserNotFoundException {
        return ResponseEntity.ok(userService.retrieveUserById(userId));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") long userId) throws UserNotFoundException {
        userService.deleteUserById(userId);
        return ResponseEntity.ok("User Deleted");
    }
}
