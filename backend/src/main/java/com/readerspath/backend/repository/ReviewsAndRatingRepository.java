package com.readerspath.backend.repository;

import com.readerspath.backend.model.AppUser;
import com.readerspath.backend.model.Book;
import com.readerspath.backend.model.ReviewsAndRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewsAndRatingRepository extends JpaRepository<ReviewsAndRating, Long> {
    ReviewsAndRating findByAppUserAndBook(AppUser appUser, Book book);
}
