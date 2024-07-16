package com.readerspath.backend.service.impl;

import com.readerspath.backend.model.AppUserModel;
import com.readerspath.backend.repository.AppUserRepository;
import com.readerspath.backend.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public String registrationService(AppUserModel appUser){
        appUser.setRole("ROLE_USER");
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUserRepository.save(appUser);
        return "User registered successfully";
    }

    @Override
    public String loginService(String email, String password){
        AppUserModel appUser = appUserRepository.findByEmail(email);
        if(appUser == null){
            throw new UsernameNotFoundException("Email is not registered. Need to register first");
        } else{
            if(!passwordEncoder.matches(password, appUser.getPassword())){
                throw new BadCredentialsException("Login failed. Wrong password");
            }
            else{
                return "Login successful";
            }
        }
    }
}
