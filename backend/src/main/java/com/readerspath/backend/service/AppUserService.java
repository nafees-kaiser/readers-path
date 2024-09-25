package com.readerspath.backend.service;

import com.readerspath.backend.model.AppUser;
import com.readerspath.backend.model.LoginResponse;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface AppUserService {

    AppUser addAppUser(AppUser appUser);

    LoginResponse loginService(String email, String password);

    AppUser getAppUserFromSession();

    AppUser getAppUserFromToken(String token);

    AppUser getAppUserByEmail(String email);

    AppUser editUser(Map<String, String> editQuery);
}
