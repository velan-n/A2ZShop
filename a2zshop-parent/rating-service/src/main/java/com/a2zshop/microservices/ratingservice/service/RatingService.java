package com.a2zshop.microservices.ratingservice.service;

import com.a2zshop.microservices.ratingservice.exception.RatingNotFoundException;
import com.a2zshop.microservices.ratingservice.model.Rating;

import java.util.List;

public interface RatingService {

    Rating addRating(Rating rating);

    List<Rating> retrieveAllRatingsForProduct(long productId) throws RatingNotFoundException;

    List<Rating> retrieveAllRatingsForUser(long userId) throws RatingNotFoundException;

    Rating retrieveRatingForUserAndProduct(long userId, long productId) throws RatingNotFoundException;

    String removeRatingForUserAndProduct(long userId, long productId);

    Double calculateAverageOfRatingsForProduct(long productId) throws RatingNotFoundException;
}
