package com.a2zshop.microservices.ratingservice.service;

import com.a2zshop.microservices.ratingservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public String removeUserRatings(long userId) {
        try {
            userRepository.deleteById(userId);
        }
        catch (Exception exception){
            return "Deletion Failed";
        }
        return "Deleted";
    }
}
