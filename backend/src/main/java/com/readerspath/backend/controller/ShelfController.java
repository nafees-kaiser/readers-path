package com.readerspath.backend.controller;

import com.readerspath.backend.model.Shelf;
import com.readerspath.backend.projection.ShelfView;
import com.readerspath.backend.service.ShelfService;
import com.readerspath.backend.util.Convertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user/shelf")
public class ShelfController {
    @Autowired
    private ShelfService shelfService;

    @PostMapping("/add")
    public ResponseEntity<?> addShelf(@RequestBody Shelf shelf, @RequestHeader("email") String email) {
        try {
            shelf = shelfService.addToShelf(shelf, email);
            ShelfView shelfView = Convertion.covertToView(shelf, ShelfView.class);
            return new ResponseEntity<>(shelfView, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/change-state")
    public ResponseEntity<?> changeShelfState(@RequestBody Shelf shelf, @RequestHeader("email") String email) {
        try {
            shelf = shelfService.changeShelfState(shelf, email);
            ShelfView shelfView = Convertion.covertToView(shelf, ShelfView.class);
            return new ResponseEntity<>(shelfView, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getShelf(@RequestHeader("email") String email) {
        try {
            List<Shelf> shelfList = shelfService.getShelf(email);
            List<ShelfView> shelfViewList = Convertion.convertToViewList(shelfList, ShelfView.class);
            return new ResponseEntity<>(shelfViewList, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteShelf(@PathVariable("id") Long id) {
        try {
            shelfService.deleteShelf(id);
            return new ResponseEntity<>(Map.of("message", "Book deleted successfully"), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
