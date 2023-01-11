package com.a2zshop.microservices.productinfoservice.repository;

import com.a2zshop.microservices.productinfoservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    public boolean existsByCategoryNameIgnoreCase(String categoryName);

}
