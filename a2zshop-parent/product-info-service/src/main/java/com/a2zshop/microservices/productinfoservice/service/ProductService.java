package com.a2zshop.microservices.productinfoservice.service;

import com.a2zshop.microservices.productinfoservice.exception.ProductNotFoundException;
import com.a2zshop.microservices.productinfoservice.model.Product;
import com.a2zshop.microservices.productinfoservice.model.SubCategory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    Product addProduct(SubCategory subCategory, Product product);

    List<Product> retrieveAllProducts();
//
    Product retrieveProductById(long productId) throws ProductNotFoundException;
//
    public void deleteProductById(long productId) throws ProductNotFoundException;

    public void setProductRating(Product product);
//
//    public Product updateProductName(long productId, String productName) throws ProductNotFoundException;
}
