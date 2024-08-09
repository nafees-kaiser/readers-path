package com.readerspath.backend.controller;

import com.readerspath.backend.model.Book;
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
@RequestMapping("/api/v1/user")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/add-book")
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        try {
            book = bookService.addBook(book);
            BookView bookView = Convertion.covertToView(book, BookView.class);
            return new ResponseEntity<>(bookView, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/books/all")
    public ResponseEntity<?> getAllBooks() {
        List<BookView> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
