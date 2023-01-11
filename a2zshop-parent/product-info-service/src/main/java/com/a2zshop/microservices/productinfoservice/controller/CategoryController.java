package com.a2zshop.microservices.productinfoservice.controller;

import com.a2zshop.microservices.productinfoservice.exception.CategoryNotFoundException;
import com.a2zshop.microservices.productinfoservice.exception.DuplicateEntryException;
import com.a2zshop.microservices.productinfoservice.exception.SubCategoryNotFoundException;
import com.a2zshop.microservices.productinfoservice.model.Category;
import com.a2zshop.microservices.productinfoservice.model.SubCategory;
import com.a2zshop.microservices.productinfoservice.service.CategoryService;
import com.a2zshop.microservices.productinfoservice.service.SubCategoryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//@RequestMapping("/admin")
public class CategoryController {

    private Logger logger= LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SubCategoryService subCategoryService;

    @PostMapping("/add-category")
    public ResponseEntity<Category> addCategory(@RequestBody @Valid Category category) throws DuplicateEntryException {
        logger.info("Adding new category...");
        return new ResponseEntity<Category>(categoryService.addCategory(category),HttpStatus.CREATED);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> retrieveAllCategories(){
        return ResponseEntity.ok(categoryService.retrieveAllCategories());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Category> retrieveCategoryById(@PathVariable long categoryId) throws CategoryNotFoundException {
        return ResponseEntity.ok(categoryService.retrieveCategoryById(categoryId));
    }

    @PostMapping("/category/{categoryId}/add-sub-category")
    public ResponseEntity<SubCategory> addSubCategory(@PathVariable long categoryId,@RequestBody @Valid SubCategory subCategory) throws CategoryNotFoundException, DuplicateEntryException {
        logger.info("Adding new sub-category...");
        return new ResponseEntity<SubCategory>(categoryService.addSubCategory(categoryId,subCategory),HttpStatus.CREATED);
    }

    @GetMapping("/category/{categoryId}/sub-categories")
    public ResponseEntity<List<SubCategory>> retrieveAllSubCategoriesForCategory(@PathVariable long categoryId) throws CategoryNotFoundException, SubCategoryNotFoundException {
        return ResponseEntity.ok(categoryService.retrieveAllSubCategoriesForCategory(categoryId));
    }

    @GetMapping("/sub-category/{subCategoryId}")
    public ResponseEntity<SubCategory> retrieveSubCategoryById(@PathVariable long subCategoryId) throws SubCategoryNotFoundException {
        return ResponseEntity.ok(subCategoryService.retrieveSubCategoryById(subCategoryId));
    }

//    @GetMapping("category/{id}/products")
//    public ResponseEntity<Product> retrieveProductsByCategory(@PathVariable("id") long categoryId) throws ProductNotFoundException {
//        return ResponseEntity.ok(categoryService.retrieveProductsByCategory(categoryId));
//    }
}
