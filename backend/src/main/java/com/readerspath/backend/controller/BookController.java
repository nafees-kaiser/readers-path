package com.readerspath.backend.controller;

import com.readerspath.backend.model.Book;
import com.readerspath.backend.model.BookFilterReq;
import com.readerspath.backend.model.Category;
import com.readerspath.backend.projection.BookView;
import com.readerspath.backend.projection.SingleBookView;
import com.readerspath.backend.service.BookService;
import com.readerspath.backend.util.Convertion;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/books/all")
    public ResponseEntity<?> getAllBooks(@RequestBody BookFilterReq req) {
        try {
//            System.out.println(req.toString());
            Page<BookView> books = bookService.getAllBooks(req);
            return new ResponseEntity<>(books, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<?> getBookById(@PathVariable("bookId") Long bookId) {
        try {
            Book book = bookService.findBookById(bookId);
            SingleBookView bookView = Convertion.covertToView(book, SingleBookView.class);
            return new ResponseEntity<>(bookView, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(value = {"/user/add-book", "/admin/add-book"}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addBook(@RequestPart("book") Book book, @RequestPart("coverImage") @Nullable MultipartFile file) {
        try {
            book = bookService.addBook(book, file);
            BookView bookView = Convertion.covertToView(book, BookView.class);
            return new ResponseEntity<>(bookView, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/admin/add-category")
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        try {
            category = bookService.addCategory(category);
            return new ResponseEntity<>(category, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/my-books")
    public ResponseEntity<?> getMyBooks() {
        try {
            List<Book> books = bookService.findMyBooks();
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
            return new ResponseEntity<>(Map.of("message", "Book deleted successfully"), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = {"/user/my-books/edit", "/admin/edit-book"}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> editBook(@RequestPart("book") Map<String, Object> updates, @RequestPart("coverImage") @Nullable MultipartFile file) {
        try {
            Book book = bookService.editBook(updates, file);
            BookView bookView = Convertion.covertToView(book, BookView.class);
            return new ResponseEntity<>(bookView, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/is-my-book/{id}")
    public ResponseEntity<?> isMyBook(@PathVariable("id") Long id) {
        try {
            Boolean isMyBook = bookService.isMyBook(id);
            return new ResponseEntity<>(isMyBook, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/user/get-rec")
    public ResponseEntity<?> getRecBook(@RequestBody BookFilterReq req) {
        try {
//            System.out.println(req.toString());
            Page<BookView> books = bookService.getRecBooks(req);
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/admin/books")
    public ResponseEntity<?> getAdminBooks() {
        try {
//            System.out.println(req.toString());
            List<BookView> books = bookService.getAdminBooks();
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
