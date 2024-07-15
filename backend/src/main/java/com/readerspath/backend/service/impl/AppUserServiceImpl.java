package com.readerspath.backend.service.impl;

import com.readerspath.backend.model.AppUserModel;
import com.readerspath.backend.repository.AppUserRepository;
import com.readerspath.backend.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public String registrationService(AppUserModel appUser){
        appUser.setRole("ROLE_USER");
        appUserRepository.save(appUser);
        return "User registered successfully";
    }

    @Override
    public String loginService(String email, String password){
        AppUserModel appUser = appUserRepository.findByEmail(email);
        if(appUser != null && appUser.getPassword().equals(password)){
            return "Login successful";
        }
        else{
            return "Login failed";
        }
    }
}
