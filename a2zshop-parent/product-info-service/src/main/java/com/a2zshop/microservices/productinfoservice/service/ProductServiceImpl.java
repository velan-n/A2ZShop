package com.a2zshop.microservices.productinfoservice.service;

import com.a2zshop.microservices.productinfoservice.dto.RatingDto;
import com.a2zshop.microservices.productinfoservice.exception.CustomFeignException;
import com.a2zshop.microservices.productinfoservice.exception.ProductNotFoundException;
import com.a2zshop.microservices.productinfoservice.model.Category;
import com.a2zshop.microservices.productinfoservice.model.Product;
import com.a2zshop.microservices.productinfoservice.model.SubCategory;
import com.a2zshop.microservices.productinfoservice.proxy.RatingControllerProxy;
import com.a2zshop.microservices.productinfoservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RatingControllerProxy ratingControllerProxy;

    @Override
    public Product addProduct(SubCategory subCategory, Product product) {
        product.setSubCategory(subCategory);
        return productRepository.save(product);
    }

    public List<Product> retrieveAllProducts() {
        List<Product> products=productRepository.findAll();
        products.stream().forEach(product -> setProductRating(product));
        return products;
    }

    public Product retrieveProductById(long productId) throws ProductNotFoundException {
        double ratingValue;

        Product product=productRepository.findById(productId)
                                         .orElseThrow(() -> new ProductNotFoundException(productId));
        setProductRating(product);
        return product;
    }

    public void setProductRating(Product product) {
        double ratingValue;
        try {
            ratingValue = ratingControllerProxy.retrieveAverageOfRatingsForProduct(product.getProductId());
        }
        catch(CustomFeignException ex){
            ratingValue=0.0;
        }
        product.setRating(ratingValue);
    }

    public void deleteProductById(long productId) throws ProductNotFoundException {
        try{
            productRepository.deleteById(productId);
        }
        catch (EmptyResultDataAccessException exception){
            throw new ProductNotFoundException(productId);
        }

        ratingControllerProxy.removeProductRatings(productId);
    }
//
//    public Product updateProductName(long productId, String productName) throws ProductNotFoundException {
//        Product product=retrieveProductById(productId);
//        product.setProductName(productName);
//        return productRepository.save(product);
//    }

}
