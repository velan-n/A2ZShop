package com.a2zshop.microservices.userinfoservice.repository;

import com.a2zshop.microservices.userinfoservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);

    boolean existsByMobileNumber(String mobileNumber);
}
