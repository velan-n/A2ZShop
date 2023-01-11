package com.a2zshop.microservices.productinfoservice.service;

import com.a2zshop.microservices.productinfoservice.exception.CategoryNotFoundException;
import com.a2zshop.microservices.productinfoservice.exception.DuplicateEntryException;
import com.a2zshop.microservices.productinfoservice.exception.ProductNotFoundException;
import com.a2zshop.microservices.productinfoservice.exception.SubCategoryNotFoundException;
import com.a2zshop.microservices.productinfoservice.model.Category;
import com.a2zshop.microservices.productinfoservice.model.Product;
import com.a2zshop.microservices.productinfoservice.model.SubCategory;
import com.a2zshop.microservices.productinfoservice.repository.SubCategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoryServiceImpl implements SubCategoryService{
    private Logger logger = LoggerFactory.getLogger(SubCategoryServiceImpl.class);
    @Autowired
    private SubCategoryRepository subCategoryRepository;
    
    @Autowired
    private ProductService productService;

    @Override
    public boolean doesSubCategoryExists(SubCategory subCategory) {
        return subCategoryRepository.existsBySubCategoryNameIgnoreCase(subCategory.getSubCategoryName());
    }

    @Override
    public SubCategory addSubCategory(Category category, SubCategory subCategory) throws DuplicateEntryException {
        checkForDuplication(subCategory);

        subCategory.setCategory(category);

        SubCategory addedSubCategory = subCategoryRepository.save(subCategory);
        logger.info("New sub-category added");
        return addedSubCategory;
    }

    @Override
    public Product addProduct(long subCategoryId, Product product) throws SubCategoryNotFoundException {
        SubCategory subCategory = retrieveSubCategoryById(subCategoryId);
        return productService.addProduct(subCategory,product);
    }

    @Override
    public SubCategory retrieveSubCategoryById(long subCategoryId) throws SubCategoryNotFoundException {
        logger.info("Finding subCategory...");
        SubCategory subCategory = subCategoryRepository.findById(subCategoryId)
                .orElseThrow(() -> {
                    logger.error("No subCategory available with id : "+subCategoryId);
                    return new SubCategoryNotFoundException(subCategoryId);
                });
        logger.info("SubCategory found");
        return subCategory;
    }

    @Override
    public List<Product> retrieveProductsBySubCategory(long subCategoryId) throws SubCategoryNotFoundException, ProductNotFoundException {
        SubCategory subCategory=retrieveSubCategoryById(subCategoryId);
        List<Product> products=subCategory.getProducts();
        if(products == null || products.isEmpty()){
            throw new ProductNotFoundException(subCategory);
        }
        products.stream().forEach(product -> productService.setProductRating(product));
        return products;
    }

    private void checkForDuplication(SubCategory subCategory) throws DuplicateEntryException {
        logger.info("Validating sub-category...");
        boolean subCategoryExists=doesSubCategoryExists(subCategory);
        if(subCategoryExists) {
            logger.error("Sub-category already exists : "+ subCategory.getSubCategoryName());
            throw new DuplicateEntryException(subCategory.getClass().getSimpleName(), subCategory.getSubCategoryName());
        }
        logger.info("Sub-category validation successful");
    }
}
