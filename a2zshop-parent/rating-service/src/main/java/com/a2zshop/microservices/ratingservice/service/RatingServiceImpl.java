package com.a2zshop.microservices.ratingservice.service;

import com.a2zshop.microservices.ratingservice.exception.RatingNotFoundException;
import com.a2zshop.microservices.ratingservice.model.Rating;
import com.a2zshop.microservices.ratingservice.model.RatingKey;
import com.a2zshop.microservices.ratingservice.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating addRating(Rating rating) {
        rating.initKey();
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> retrieveAllRatingsForProduct(long productId) throws RatingNotFoundException {
        List<Rating> ratings = ratingRepository.findAllByProduct_ProductId(productId);
        if (ratings == null || ratings.isEmpty()) {
            throw new RatingNotFoundException();
        }
        return ratings;
    }

    @Override
    public List<Rating> retrieveAllRatingsForUser(long userId) throws RatingNotFoundException {
        List<Rating> ratings = ratingRepository.findAllByUser_UserId(userId);
        if (ratings == null || ratings.isEmpty()) {
            throw new RatingNotFoundException();
        }
        return ratings;
    }

    @Override
    public Rating retrieveRatingForUserAndProduct(long userId, long productId) throws RatingNotFoundException {
        RatingKey ratingKey = new RatingKey(userId, productId);
        Rating rating = ratingRepository.findByRatingKey(ratingKey);
        if (rating == null) {
            throw new RatingNotFoundException();
        }
        return rating;
    }

    @Override
    public String removeRatingForUserAndProduct(long userId, long productId) {
        RatingKey ratingKey = new RatingKey(userId, productId);
        try {
            ratingRepository.deleteById(ratingKey);
        } catch (Exception exception) {
            return "Deletion Failed";
        }
        return "Deleted";
    }

    @Override
    public Double calculateAverageOfRatingsForProduct(long productId) throws RatingNotFoundException {
        Double averageOfRatings = ratingRepository.averageOfRatings(productId);

        if (averageOfRatings == null) {
            throw new RatingNotFoundException();
        }
        return averageOfRatings;
    }

}
