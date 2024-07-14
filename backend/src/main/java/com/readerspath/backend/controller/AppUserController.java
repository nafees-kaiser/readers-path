package com.readerspath.backend.controller;

import com.readerspath.backend.model.AppUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AppUserController {
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AppUser user) {
        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }
}
