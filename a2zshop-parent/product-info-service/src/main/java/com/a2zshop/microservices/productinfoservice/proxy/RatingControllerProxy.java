package com.a2zshop.microservices.productinfoservice.proxy;

import com.a2zshop.microservices.productinfoservice.dto.RatingDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(name = "rating-service")
public interface RatingControllerProxy {

//    @PostMapping("/add-rating")
//    public RatingDto addRating(@RequestBody RatingDto rating);

    @GetMapping("/ratings-average/product/{productId}")
    public Double retrieveAverageOfRatingsForProduct(@PathVariable long productId);

    @DeleteMapping("/remove-ratings/product/{productId}")
    public ResponseEntity<String> removeProductRatings(@PathVariable long productId);
}
