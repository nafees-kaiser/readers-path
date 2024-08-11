package com.readerspath.backend.service.impl;

import com.readerspath.backend.exception.UserNotFoundException;
import com.readerspath.backend.model.AppUser;
import com.readerspath.backend.repository.AppUserRepository;
import com.readerspath.backend.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AppUser addAppUser(AppUser appUser) {
        appUser.setRole("ROLE_USER");
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return appUserRepository.save(appUser);
    }

    @Override
    public AppUser loginService(String email, String password) {
//        String email = appUser.getEmail();
//        String password = appUser.getPassword();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        authenticationManager.authenticate(authenticationToken);
        return getAppUserByEmail(email);
    }

    @Override
    public AppUser getAppUserByEmail(String email) throws UserNotFoundException {
        AppUser appUser = appUserRepository.findByEmail(email);
        if (appUser == null) {
            throw new UserNotFoundException("User not found");
        }
        return appUser;
    }


}
