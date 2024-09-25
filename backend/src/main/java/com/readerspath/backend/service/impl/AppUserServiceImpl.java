package com.readerspath.backend.service.impl;

import com.readerspath.backend.exception.UserNotFoundException;
import com.readerspath.backend.model.AppUser;
import com.readerspath.backend.model.LoginResponse;
import com.readerspath.backend.repository.AppUserRepository;
import com.readerspath.backend.service.AppUserService;
import com.readerspath.backend.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Override
    public AppUser addAppUser(AppUser appUser) {
        appUser.setRole("ROLE_USER");
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return appUserRepository.save(appUser);
    }

    @Override
    public LoginResponse loginService(String email, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        authenticationManager.authenticate(authenticationToken);
        String token = jwtTokenService.createToken(email);
        String role = getAppUserByEmail(email).getRole();
        return new LoginResponse(token, role, jwtTokenService.getExpirationDateFromToken(token));
    }

    @Override
    public AppUser getAppUserFromSession() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new UserNotFoundException("User not found");
        }
        String email = (String) authentication.getPrincipal();
        if (email == null) {
            throw new UserNotFoundException("User not found");
        }
        return this.getAppUserByEmail(email);
    }

    @Override
    public AppUser getAppUserFromToken(String token) {
        String email = jwtTokenService.getUsernameFromToken(token.substring(7));
        if (email != null) {
            return this.getAppUserByEmail(email);
        } else {
            throw new UserNotFoundException("User is not verified");
        }

    }

    @Override
    public AppUser getAppUserByEmail(String email) throws UserNotFoundException {
        AppUser appUser = appUserRepository.findByEmail(email);
        if (appUser == null) {
            throw new UserNotFoundException("User not found");
        }
        return appUser;
    }

    @Override
    public AppUser editUser(Map<String, String> editQuery) {
        AppUser appUser = this.getAppUserFromSession();

        editQuery.forEach((key, value) -> {
            if (value != null && !value.isEmpty()) {
                switch (key) {
                    case "email":
                        appUser.setEmail(value);
                        break;
                    case "name":
                        appUser.setName(value);
                        break;
                    default:
                        throw new UserNotFoundException("Unable to edit user");
                }
            }
        });
        return appUserRepository.save(appUser);
    }


}
