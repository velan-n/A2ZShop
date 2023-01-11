package com.a2zshop.microservices.ratingservice.repository;

import com.a2zshop.microservices.ratingservice.model.Rating;
import com.a2zshop.microservices.ratingservice.model.RatingKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, RatingKey> {

    List<Rating> findAllByProduct_ProductId(long productId);

    List<Rating> findAllByUser_UserId(long userId);

    Rating findByRatingKey(RatingKey ratingKey);

    @Query("SELECT ROUND(AVG(rating.ratingValue), 1) FROM Rating rating WHERE rating.ratingKey.productId = ?1")
    Double averageOfRatings(long productId);
}
