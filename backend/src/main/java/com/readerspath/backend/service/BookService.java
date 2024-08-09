package com.readerspath.backend.service;

import com.readerspath.backend.model.Book;
import com.readerspath.backend.projection.BookView;

import java.util.List;

public interface BookService {
    Book addBook(Book book);

    List<BookView> getAllBooks();
}
