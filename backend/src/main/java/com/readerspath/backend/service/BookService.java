package com.readerspath.backend.service;

import com.readerspath.backend.model.Book;
import com.readerspath.backend.model.BookFilterReq;
import com.readerspath.backend.model.Category;
import com.readerspath.backend.projection.BookView;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public interface BookService {
    Book addBook(Book book, MultipartFile file) throws IOException;

    Page<BookView> getAllBooks(BookFilterReq req);

    Book findBookById(Long bookId);

    Category addCategory(Category category);

    List<Book> findMyBooks();

    void deleteBookById(Long bookId);

    void updateOverAllRating(Book book);

    Book editBook(Map<String, Object> updates, MultipartFile file) throws IOException;

    Boolean isMyBook(Long id);

//    void addRecommendation();

    void addRecommendation(String title, int numOfBooks);

    Page<BookView> getRecBooks(BookFilterReq req);

    List<BookView> getAdminBooks();
}
