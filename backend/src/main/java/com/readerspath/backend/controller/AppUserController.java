package com.readerspath.backend.controller;

import com.readerspath.backend.model.AppUserModel;
import com.readerspath.backend.service.AppUserService;
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
    public ResponseEntity<?> register(@RequestBody AppUserModel appUserModel) {
        try {
            String msg = appUserService.registrationService(appUserModel);
            return new ResponseEntity<>(Map.of("message", msg), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AppUserModel appUserModel) {
        try {
            String msg = appUserService.loginService(appUserModel.getEmail(), appUserModel.getPassword());
            return new ResponseEntity<>(Map.of("message", msg), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
