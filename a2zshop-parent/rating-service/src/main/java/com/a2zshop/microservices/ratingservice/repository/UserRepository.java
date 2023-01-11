package com.a2zshop.microservices.ratingservice.repository;

import com.a2zshop.microservices.ratingservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
