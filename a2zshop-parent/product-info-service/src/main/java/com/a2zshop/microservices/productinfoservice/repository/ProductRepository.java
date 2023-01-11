package com.a2zshop.microservices.productinfoservice.repository;

import com.a2zshop.microservices.productinfoservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
