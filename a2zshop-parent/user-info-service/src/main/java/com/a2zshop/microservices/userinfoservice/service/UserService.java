package com.a2zshop.microservices.userinfoservice.service;

import com.a2zshop.microservices.userinfoservice.dto.request.UserRequestDTO;
import com.a2zshop.microservices.userinfoservice.dto.response.UserResponseDTO;
import com.a2zshop.microservices.userinfoservice.exception.UserNotFoundException;
import com.a2zshop.microservices.userinfoservice.model.Address;
import com.a2zshop.microservices.userinfoservice.model.User;
import com.a2zshop.microservices.userinfoservice.repository.UserRepository;
import org.hibernate.DuplicateMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponseDTO saveUser(UserRequestDTO userRequestDto) {
        User user=User.builder()
                .userName(userRequestDto.getUserName())
                .email(userRequestDto.getEmail())
                .mobileNumber(userRequestDto.getMobileNumber())
                .address(userRequestDto.getAddress())
                .password(userRequestDto.getPassword())
                .build();

        duplicateValidation(user);

        User savedUser=userRepository.save(user);

        return UserResponseDTO.builder()
                        .userName(savedUser.getUserName())
                        .email(savedUser.getEmail())
                        .mobileNumber(savedUser.getMobileNumber())
                        .address(savedUser.getAddress())
                        .build();
    }

    private void duplicateValidation(User user) {
        boolean existsByEmail = userRepository.existsByEmail(user.getEmail());
        boolean existsByMobile = userRepository.existsByMobileNumber(user.getMobileNumber());
        if(existsByEmail)
            throw new DuplicateMappingException("User with same email already exists","","");
        else if (existsByMobile)
            throw new DuplicateMappingException("User with same mobileNumber already exists","","");

    }

    public List<UserResponseDTO> retrieveAllUsers() {
        List<User> users=userRepository.findAll();

        return users.stream().map(user -> mapToUserResponseDTO(user)).toList();
    }

    private UserResponseDTO mapToUserResponseDTO(User user) {
        return UserResponseDTO.builder()
                .userName(user.getUserName())
                .email(user.getEmail())
                .mobileNumber(user.getMobileNumber())
                .address(user.getAddress())
                .build();
    }

    public UserResponseDTO retrieveUserById(long userId) throws UserNotFoundException {
        User user=userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        return UserResponseDTO.builder()
                .userName(user.getUserName())
                .email(user.getEmail())
                .mobileNumber(user.getMobileNumber())
                .address(user.getAddress())
                .build();
    }

    public void deleteUserById(long userId) throws UserNotFoundException {
        try {
            userRepository.deleteById(userId);
        }
        catch (EmptyResultDataAccessException exception){
            throw new UserNotFoundException(userId);
        }
    }
}
