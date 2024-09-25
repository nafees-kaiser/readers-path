package com.readerspath.backend.controller;

import com.readerspath.backend.model.Author;
import com.readerspath.backend.projection.AuthorView;
import com.readerspath.backend.service.AuthorService;
import com.readerspath.backend.util.Convertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/author")
    public ResponseEntity<?> getAuthors() {
        try {
            List<Author> authors = authorService.getAuthors();
            List<AuthorView> authorViews = Convertion.convertToViewList(authors, AuthorView.class);
            return new ResponseEntity<>(authorViews, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
