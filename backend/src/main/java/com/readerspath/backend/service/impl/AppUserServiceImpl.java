package com.readerspath.backend.service.impl;

import com.readerspath.backend.model.AppUser;
import com.readerspath.backend.model.Author;
import com.readerspath.backend.model.Category;
import com.readerspath.backend.model.Preference;
import com.readerspath.backend.repository.AppUserRepository;
import com.readerspath.backend.service.AppUserService;
import com.readerspath.backend.service.AuthorService;
import com.readerspath.backend.service.CategoryService;
import com.readerspath.backend.service.PreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private PreferenceService preferenceService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public AppUser addAppUser(AppUser appUser) {
        appUser.setRole("ROLE_USER");
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return appUserRepository.save(appUser);
    }

    @Override
    public Preference registrationService(Preference preference) {
        AppUser appUser = addAppUser(preference.getAppUser());
        List<Category> categories = preference.getFavouriteCategories().stream()
                .map(category -> categoryService.findCategoryByName(category.getName()))
                .toList();
        List<Author> authors = preference.getFavouriteAuthors().stream()
                .map(author -> authorService.findAuthorByName(author.getName()))
                .toList();
        return preferenceService.addPreference(appUser, categories, authors);
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
    public AppUser getAppUserByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }
}
