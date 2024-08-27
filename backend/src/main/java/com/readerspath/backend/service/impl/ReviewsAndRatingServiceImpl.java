package com.readerspath.backend.service.impl;

import com.readerspath.backend.model.AppUser;
import com.readerspath.backend.model.Book;
import com.readerspath.backend.model.ReviewsAndRating;
import com.readerspath.backend.repository.ReviewsAndRatingRepository;
import com.readerspath.backend.service.AppUserService;
import com.readerspath.backend.service.BookService;
import com.readerspath.backend.service.ReviewsAndRatingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewsAndRatingServiceImpl implements ReviewsAndRatingService {
    @Autowired
    private ReviewsAndRatingRepository reviewsAndRatingRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private AppUserService appUserService;

    @Transactional
    @Override
    public ReviewsAndRating addReview(ReviewsAndRating review) {
        AppUser appUser = appUserService.getAppUserFromSession();
        Book book = bookService.findBookById(review.getBook().getId());
        review.setBook(book);
        review.setAppUser(appUser);
        ReviewsAndRating reviewsAndRating = reviewsAndRatingRepository.save(review);
        bookService.updateOverAllRating(book);
        return reviewsAndRating;
    }

    @Transactional
    @Override
    public Boolean isAlreadyReviewed(Long id) {
        Book book = bookService.findBookById(id);
        AppUser appUser = appUserService.getAppUserFromSession();
        ReviewsAndRating reviewsAndRating = reviewsAndRatingRepository.findByAppUserAndBook(appUser, book);
        return reviewsAndRating != null;
    }
}
