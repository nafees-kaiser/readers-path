package com.readerspath.backend.controller;

import com.readerspath.backend.model.Book;
import com.readerspath.backend.model.Shelf;
import com.readerspath.backend.projection.ShelfView;
import com.readerspath.backend.service.ShelfService;
import com.readerspath.backend.util.Convertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/user/shelf")
public class ShelfController {
    @Autowired
    private ShelfService shelfService;

    @PostMapping("/add")
    public ResponseEntity<?> addShelf(@RequestBody Book book, @RequestHeader("email") String email) {
        try {
            Shelf shelf = shelfService.addToShelf(book, email);
            ShelfView shelfView = Convertion.covertToView(shelf, ShelfView.class);
            return new ResponseEntity<>(shelfView, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/change-state")
    public ResponseEntity<?> changeShelfState(@RequestBody Book book, @RequestHeader("email") String email) {
        try {
            Shelf shelf = shelfService.changeShelfState(book, email);
            ShelfView shelfView = Convertion.covertToView(shelf, ShelfView.class);
            return new ResponseEntity<>(shelfView, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getShelf(@RequestHeader("email") String email) {
        try {
            Shelf shelf = shelfService.getShelf(email);
            ShelfView shelfView = Convertion.covertToView(shelf, ShelfView.class);
            return new ResponseEntity<>(shelfView, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
