package com.readerspath.backend.controller;

import com.readerspath.backend.model.AppUser;
import com.readerspath.backend.projection.AppUserView;
import com.readerspath.backend.service.AppUserService;
import com.readerspath.backend.util.Convertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class AppUserController {
    @Autowired
    private AppUserService appUserService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AppUser appUser) {
        try {
            appUser = appUserService.loginService(appUser.getEmail(), appUser.getPassword());
            AppUserView appUserView = Convertion.covertToView(appUser, AppUserView.class);
            return new ResponseEntity<>(appUserView, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
