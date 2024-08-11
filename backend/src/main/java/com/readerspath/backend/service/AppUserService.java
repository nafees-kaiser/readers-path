package com.readerspath.backend.service;

import com.readerspath.backend.model.AppUser;
import org.springframework.stereotype.Service;

@Service
public interface AppUserService {

    AppUser addAppUser(AppUser appUser);

    AppUser loginService(String email, String password);

    AppUser getAppUserByEmail(String email);

}
