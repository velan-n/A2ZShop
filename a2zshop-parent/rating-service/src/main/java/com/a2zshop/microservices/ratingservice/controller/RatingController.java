package com.a2zshop.microservices.ratingservice.controller;


import com.a2zshop.microservices.ratingservice.exception.RatingNotFoundException;
import com.a2zshop.microservices.ratingservice.model.Rating;
import com.a2zshop.microservices.ratingservice.service.ProductService;
import com.a2zshop.microservices.ratingservice.service.RatingService;
import com.a2zshop.microservices.ratingservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RatingController {
    private Logger logger= LoggerFactory.getLogger(RatingController.class);

    @Autowired
    private RatingService ratingService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @PostMapping("/add-rating")
    public ResponseEntity<Rating> addRating(@RequestBody @Valid Rating rating) {
        logger.info("Add Rating");
        return new ResponseEntity(ratingService.addRating(rating), HttpStatus.CREATED);
    }

    @GetMapping("/ratings/product/{productId}")
    public ResponseEntity<List<Rating>> retrieveAllRatingsForProduct(@PathVariable long productId) throws RatingNotFoundException {
        return ResponseEntity.ok(ratingService.retrieveAllRatingsForProduct(productId));
    }

    @GetMapping("/ratings/user/{userId}")
    public ResponseEntity<List<Rating>> retrieveAllRatingsForUser(@PathVariable long userId) throws RatingNotFoundException {
        return ResponseEntity.ok(ratingService.retrieveAllRatingsForUser(userId));
    }

    @GetMapping("/rating/user/{userId}/product/{productId}")
    public ResponseEntity<Rating> retrieveRatingForUserAndProduct(@PathVariable long userId, @PathVariable long productId) throws RatingNotFoundException {
        return ResponseEntity.ok(ratingService.retrieveRatingForUserAndProduct(userId, productId));
    }

    @GetMapping("/ratings-average/product/{productId}")
    public ResponseEntity<Double> retrieveAverageOfRatingsForProduct(@PathVariable long productId) throws RatingNotFoundException {
        return ResponseEntity.ok(ratingService.calculateAverageOfRatingsForProduct(productId));
    }

    @DeleteMapping("/remove-rating/user/{userId}/product/{productId}")
    public ResponseEntity<String> removeRatingForUserAndProduct(@PathVariable long userId, @PathVariable long productId) {
        return ResponseEntity.ok(ratingService.removeRatingForUserAndProduct(userId, productId));
    }

    @DeleteMapping("/remove-ratings/user/{userId}")
    public ResponseEntity<String> removeUserRatings(@PathVariable long userId)  {
        return ResponseEntity.ok(userService.removeUserRatings(userId));
    }

    @DeleteMapping("/remove-ratings/product/{productId}")
    public ResponseEntity<String> removeProductRatings(@PathVariable long productId) {
        return ResponseEntity.ok(productService.removeProductRatings(productId));
    }
}
