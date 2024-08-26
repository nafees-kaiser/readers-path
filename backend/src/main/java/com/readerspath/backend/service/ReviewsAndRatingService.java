package com.readerspath.backend.service;

import com.readerspath.backend.model.ReviewsAndRating;

public interface ReviewsAndRatingService {
    ReviewsAndRating addReview(ReviewsAndRating review);

    Boolean isAlreadyReviewed(Long id);
}
