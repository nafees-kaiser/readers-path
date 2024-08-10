package com.readerspath.backend.service.impl;

import com.readerspath.backend.exception.UserNotFoundException;
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
    public AppUser getAppUserByEmail(String email) throws UserNotFoundException {
        AppUser appUser = appUserRepository.findByEmail(email);
        if (appUser == null) {
            throw new UserNotFoundException("User not found");
        }
        return appUser;
    }

    @Override
    public Preference editPreference(Preference preference, String email) {
        AppUser appUser = this.getAppUserByEmail(email);
        List<Category> categories = null;
        List<Author> authors = null;
        if (preference.getFavouriteCategories() != null && !preference.getFavouriteCategories().isEmpty()) {
            categories = preference.getFavouriteCategories().stream()
                    .map(category -> categoryService.findCategoryByName(category.getName()))
                    .toList();
        }

        if (preference.getFavouriteAuthors() != null && !preference.getFavouriteAuthors().isEmpty()) {
            authors = preference.getFavouriteAuthors().stream()
                    .map(author -> authorService.findAuthorByName(author.getName()))
                    .toList();
        }

        return preferenceService.editPreference(appUser, categories, authors);
    }

    @Override
    public Preference getPreference(String email) {
        AppUser appUser = this.getAppUserByEmail(email);
        return preferenceService.findPreferenceByAppUser(appUser);
    }
}
