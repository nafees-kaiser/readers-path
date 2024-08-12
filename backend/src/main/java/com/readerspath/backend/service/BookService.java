package com.readerspath.backend.service;

import com.readerspath.backend.model.Book;
import com.readerspath.backend.model.BookFilterReq;
import com.readerspath.backend.model.Category;
import com.readerspath.backend.projection.BookView;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface BookService {
    Book addBook(Book book, MultipartFile file) throws IOException;

    List<BookView> getAllBooks(BookFilterReq req);

    Book findBookById(Long bookId);

    Category addCategory(Category category);

    List<Book> findMyBooks();

    void deleteBookById(Long bookId);

    void updateOverAllRating(Book book);

    Book editBook(Map<String, Object> updates, MultipartFile file) throws IOException;
}
