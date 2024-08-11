package com.readerspath.backend.service;

import com.readerspath.backend.model.Book;
import com.readerspath.backend.model.BookFilterReq;
import com.readerspath.backend.model.Category;
import com.readerspath.backend.projection.BookView;

import java.util.List;
import java.util.Map;

public interface BookService {
    Book addBook(String email, Book book);

    List<BookView> getAllBooks(BookFilterReq req);

    Book findBookById(Long bookId);

    Category addCategory(Category category);

    List<Book> findMyBooks(String email);

    void deleteBookById(Long bookId);

    void updateOverAllRating(Book book);

    Book editBook(Map<String, Object> updates);
}
