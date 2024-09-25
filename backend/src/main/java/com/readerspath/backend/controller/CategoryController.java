package com.readerspath.backend.controller;

import com.readerspath.backend.model.Category;
import com.readerspath.backend.service.CategoryService;
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
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public ResponseEntity<?> getCategories() {
        try {
            List<Category> categories = categoryService.getCategories();
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
