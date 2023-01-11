package com.a2zshop.microservices.ratingservice.service;

import com.a2zshop.microservices.ratingservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public String removeProductRatings(long productId) {
        try{
            productRepository.deleteById(productId);
        }
        catch (Exception exception){
            return "Rating Deletion Failed";
        }
        return "Rating Deleted";
    }
}
