package com.readerspath.backend.controller;

import com.readerspath.backend.model.Author;
import com.readerspath.backend.model.Category;
import com.readerspath.backend.model.Preference;
import com.readerspath.backend.projection.PreferenceView;
import com.readerspath.backend.service.AppUserService;
import com.readerspath.backend.service.AuthorService;
import com.readerspath.backend.service.CategoryService;
import com.readerspath.backend.service.PreferenceService;
import com.readerspath.backend.util.Convertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class PreferenceController {
    @Autowired
    private PreferenceService preferenceService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AppUserService appUserService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Preference preference) {
        try {
            preference = preferenceService.register(preference);
            PreferenceView preferenceView = Convertion.covertToView(preference, PreferenceView.class);
            return new ResponseEntity<>(preferenceView, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/category")
    public ResponseEntity<?> getCategories() {
        try {
            List<Category> categories = categoryService.getCategories();
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/author")
    public ResponseEntity<?> getAuthors() {
        try {
            List<Author> categories = authorService.getAuthors();
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/user/edit-preference")
    public ResponseEntity<?> editPreference(@RequestBody Map<String, Object> updates, @RequestHeader("email") String email) {
        try {
            Preference preference = preferenceService.editPreference(updates);
            PreferenceView preferenceView = Convertion.covertToView(preference, PreferenceView.class);
            return new ResponseEntity<>(preferenceView, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/preference")
    public ResponseEntity<?> getPreference(@RequestHeader("email") String email) {
        try {
            Preference preference = preferenceService.getPreference(email);
            PreferenceView preferenceView = Convertion.covertToView(preference, PreferenceView.class);
            return new ResponseEntity<>(preferenceView, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
