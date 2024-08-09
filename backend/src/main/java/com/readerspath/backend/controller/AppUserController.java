package com.readerspath.backend.controller;

import com.readerspath.backend.model.AppUser;
import com.readerspath.backend.model.Preference;
import com.readerspath.backend.projection.PreferenceView;
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
    public ResponseEntity<?> register(@RequestBody Preference preference) {
        try {
            preference = appUserService.registrationService(preference);
            PreferenceView preferenceView = Convertion.covertToView(preference, PreferenceView.class);
            return new ResponseEntity<>(preferenceView, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AppUser appUser) {
        try {
            String msg = appUserService.loginService(appUser.getEmail(), appUser.getPassword());
            return new ResponseEntity<>(Map.of("message", msg), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
