package com.readerspath.backend.repository;

import com.readerspath.backend.model.Author;
import com.readerspath.backend.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, BookFilterCustomRepository {
    List<Book> findAllByAuthor(Author author);

    List<Book> findAllByOrderByOverAllRatingAsc();
}
