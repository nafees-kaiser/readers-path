package com.readerspath.backend.controller;

import com.readerspath.backend.model.AppUser;
import com.readerspath.backend.model.LoginResponse;
import com.readerspath.backend.projection.AppUserView;
import com.readerspath.backend.service.AppUserService;
import com.readerspath.backend.util.Convertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class AppUserController {
    @Autowired
    private AppUserService appUserService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AppUser appUser) {
        try {
            appUserService.addAppUser(appUser);
            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AppUser appUser) {
        try {
            LoginResponse response = appUserService.loginService(appUser.getEmail(), appUser.getPassword());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUser() {
        try {
            AppUser appUser = appUserService.getAppUserFromSession();
            AppUserView appUserView = Convertion.covertToView(appUser, AppUserView.class);
            return new ResponseEntity<>(appUserView, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
