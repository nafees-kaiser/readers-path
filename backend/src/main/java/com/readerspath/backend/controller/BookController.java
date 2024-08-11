package com.readerspath.backend.controller;

import com.readerspath.backend.model.Book;
import com.readerspath.backend.model.BookFilterReq;
import com.readerspath.backend.model.Category;
import com.readerspath.backend.projection.BookView;
import com.readerspath.backend.service.BookService;
import com.readerspath.backend.util.Convertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/books/all")
    public ResponseEntity<?> getAllBooks(@RequestBody BookFilterReq req) {
        try {
            List<BookView> books = bookService.getAllBooks(req);
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<?> getBookById(@PathVariable("bookId") Long bookId) {
        try {
            Book book = bookService.findBookById(bookId);
            BookView bookView = Convertion.covertToView(book, BookView.class);
            return new ResponseEntity<>(bookView, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping({"/user/add-book", "/admin/add-book"})
    public ResponseEntity<?> addBook(@RequestHeader("email") String email, @RequestBody Book book) {
        try {
            book = bookService.addBook(email, book);
            BookView bookView = Convertion.covertToView(book, BookView.class);
            return new ResponseEntity<>(bookView, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/admin/add-category")
    public ResponseEntity<?> addCategory(@RequestHeader("email") String email, @RequestBody Category category) {
        try {
            category = bookService.addCategory(category);
            return new ResponseEntity<>(category, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/my-books")
    public ResponseEntity<?> getMyBooks(@RequestHeader("email") String email) {
        try {
            List<Book> books = bookService.findMyBooks(email);
            List<BookView> bookViews = Convertion.convertToViewList(books, BookView.class);
            return new ResponseEntity<>(bookViews, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/user/my-books/delete/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable("bookId") Long bookId) {
        try {
            bookService.deleteBookById(bookId);
            return new ResponseEntity<>(Map.of("message", "Book deleted successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
