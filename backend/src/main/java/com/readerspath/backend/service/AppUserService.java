package com.readerspath.backend.service;

import com.readerspath.backend.model.AppUserModel;
import org.springframework.stereotype.Service;

@Service
public interface AppUserService {

    String registrationService(AppUserModel appUser);

    String loginService(String email, String password);
}
