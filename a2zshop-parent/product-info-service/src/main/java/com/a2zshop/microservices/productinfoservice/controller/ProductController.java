package com.a2zshop.microservices.productinfoservice.controller;

import com.a2zshop.microservices.productinfoservice.dto.RatingDto;
import com.a2zshop.microservices.productinfoservice.exception.CategoryNotFoundException;
import com.a2zshop.microservices.productinfoservice.exception.ProductNotFoundException;
import com.a2zshop.microservices.productinfoservice.exception.SubCategoryNotFoundException;
import com.a2zshop.microservices.productinfoservice.model.Product;
import com.a2zshop.microservices.productinfoservice.proxy.RatingControllerProxy;
import com.a2zshop.microservices.productinfoservice.service.ProductService;
import com.a2zshop.microservices.productinfoservice.service.SubCategoryService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//@RequestMapping("/admin")
//@Validated
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private SubCategoryService subCategoryService;

//    @Autowired
//    private RatingControllerProxy ratingControllerProxy;

    @PostMapping("/sub-category/{subCategoryId}/add-product")
    @ApiResponse
    public ResponseEntity<Product> addProduct(@PathVariable long subCategoryId,
                                               @Valid @RequestBody Product product ) throws CategoryNotFoundException, SubCategoryNotFoundException {
        return new ResponseEntity<Product>(subCategoryService.addProduct(subCategoryId,product), HttpStatus.CREATED);
    }

    @GetMapping("/sub-category/{subCategoryId}/products")
    public ResponseEntity<List<Product>> retrieveProductsBySubCategory(@PathVariable long subCategoryId) throws SubCategoryNotFoundException, ProductNotFoundException {
        return ResponseEntity.ok(subCategoryService.retrieveProductsBySubCategory(subCategoryId));
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> retrieveAllProducts(){
        return ResponseEntity.ok(productService.retrieveAllProducts());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> retrieveProductById(@PathVariable("id") long productId) throws ProductNotFoundException {
        return ResponseEntity.ok(productService.retrieveProductById(productId));
    }

    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity deleteProductById(@PathVariable("id") long productId) throws ProductNotFoundException {
        productService.deleteProductById(productId);
        return ResponseEntity.ok("Product deleted");
    }

//    @PostMapping("product/add-rating")
//    public ResponseEntity<RatingDto> addRating(@RequestBody RatingDto rating){
//        return new ResponseEntity(ratingControllerProxy.addRating(rating),HttpStatus.CREATED);
//    }


//    @PutMapping("/product/{id}/update-name")
//    public ResponseEntity updateProductName(@PathVariable("id") long productId,
//                                            @RequestParam("name")
//                                            @Size(min = 2,max = 50)
//                                            @WhitespaceValidation
//                                            @NotBlank String productName
//                                            ) throws ProductNotFoundException {
//        return ResponseEntity.ok(productService.updateProductName(productId,productName));
//    }

}
