package com.readerspath.backend.repository;

import com.readerspath.backend.model.RecommendedBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendedBooksRepository extends JpaRepository<RecommendedBooks, Long> {
}
