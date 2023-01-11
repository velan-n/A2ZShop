package com.a2zshop.microservices.ratingservice.repository;

import com.a2zshop.microservices.ratingservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
