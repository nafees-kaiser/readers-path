package com.readerspath.backend.service;

import com.readerspath.backend.model.ReviewsAndRating;

public interface ReviewsAndRatingService {
    ReviewsAndRating addReview(String email, ReviewsAndRating review);
}
