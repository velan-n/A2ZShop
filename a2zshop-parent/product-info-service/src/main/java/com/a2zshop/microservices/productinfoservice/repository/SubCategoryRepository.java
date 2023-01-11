package com.a2zshop.microservices.productinfoservice.repository;

import com.a2zshop.microservices.productinfoservice.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    public boolean existsBySubCategoryNameIgnoreCase(String subCategoryName);
}
