package com.a2zshop.microservices.productinfoservice.service;

import com.a2zshop.microservices.productinfoservice.exception.DuplicateEntryException;
import com.a2zshop.microservices.productinfoservice.exception.ProductNotFoundException;
import com.a2zshop.microservices.productinfoservice.exception.SubCategoryNotFoundException;
import com.a2zshop.microservices.productinfoservice.model.Category;
import com.a2zshop.microservices.productinfoservice.model.Product;
import com.a2zshop.microservices.productinfoservice.model.SubCategory;

import java.util.List;

public interface SubCategoryService {
    boolean doesSubCategoryExists(SubCategory subCategory);

    SubCategory addSubCategory(Category category, SubCategory subCategory) throws DuplicateEntryException;

    Product addProduct(long subCategoryId, Product product) throws SubCategoryNotFoundException;

    SubCategory retrieveSubCategoryById(long subCategoryId) throws SubCategoryNotFoundException;

    List<Product> retrieveProductsBySubCategory(long subCategoryId) throws SubCategoryNotFoundException, ProductNotFoundException;
}
