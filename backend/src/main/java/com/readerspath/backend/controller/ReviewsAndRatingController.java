package com.readerspath.backend.controller;

import com.readerspath.backend.model.ReviewsAndRating;
import com.readerspath.backend.projection.ReviewsAndRatingView;
import com.readerspath.backend.service.ReviewsAndRatingService;
import com.readerspath.backend.util.Convertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class ReviewsAndRatingController {
    @Autowired
    private ReviewsAndRatingService reviewsAndRatingService;

    @PostMapping("/add-review")
    public ResponseEntity<?> addReview(@RequestHeader("email") String email, @RequestBody ReviewsAndRating review) {
        try {
            ReviewsAndRating reviewsAndRating = reviewsAndRatingService.addReview(email, review);
            ReviewsAndRatingView reviewsAndRatingView = Convertion.covertToView(reviewsAndRating, ReviewsAndRatingView.class);
            return new ResponseEntity<>(reviewsAndRatingView, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}