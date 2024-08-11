package com.readerspath.backend.service.impl;

import com.readerspath.backend.exception.PreferenceNotFoundException;
import com.readerspath.backend.model.AppUser;
import com.readerspath.backend.model.Author;
import com.readerspath.backend.model.Category;
import com.readerspath.backend.model.Preference;
import com.readerspath.backend.repository.PreferenceRepository;
import com.readerspath.backend.service.AppUserService;
import com.readerspath.backend.service.AuthorService;
import com.readerspath.backend.service.CategoryService;
import com.readerspath.backend.service.PreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PreferenceServiceImpl implements PreferenceService {
    @Autowired
    private PreferenceRepository preferenceRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AppUserService appUserService;

    @Override
    public Preference register(Preference preference) {
        AppUser appUser = appUserService.addAppUser(preference.getAppUser());
        List<Category> categories = preference.getFavouriteCategories().stream()
                .map(category -> categoryService.findCategoryByName(category.getName()))
                .toList();
        List<Author> authors = preference.getFavouriteAuthors().stream()
                .map(author -> authorService.findAuthorByName(author.getName()))
                .toList();
        return this.addPreference(appUser, categories, authors);
    }

    @Override
    public Preference addPreference(AppUser appUser, List<Category> categories, List<Author> authors) {
        Preference preference = new Preference(appUser, categories, authors);
        return preferenceRepository.save(preference);
    }

    @Override
    public Preference findPreferenceByAppUser(AppUser appUser) {
        return preferenceRepository.findByAppUser(appUser);
    }

    @Override
    public Preference getPreference() {
        AppUser appUser = appUserService.getAppUserFromSession();
        return this.findPreferenceByAppUser(appUser);
    }

    @Override
    public Preference editPreference(Map<String, Object> updates) {
        Preference preference = preferenceRepository.findById(Long.valueOf((int) updates.get("id")))
                .orElseThrow(() -> new PreferenceNotFoundException("Preference not found"));

        updates.forEach((key, value) -> {
            if (value != null) {
                switch (key) {
                    case "id":
                        break;
                    case "favouriteCategories":
                        List<Map<String, Object>> categories = (List<Map<String, Object>>) value;
                        List<Category> categoriesToAdd = new ArrayList<>();
                        if (!categories.isEmpty()) {
                            categories.forEach(c -> {
                                Category category = categoryService.findCategoryByName((String) c.get("name"));
                                categoriesToAdd.add(category);
                            });
                            preference.setFavouriteCategories(categoriesToAdd);
                        }
                        break;
                    case "favouriteAuthors":
                        List<Map<String, Object>> authors = (List<Map<String, Object>>) value;
                        List<Author> authorsToAdd = new ArrayList<>();
                        if (!authors.isEmpty()) {
                            authors.forEach(a -> {
                                Author author = authorService.findAuthorByName((String) a.get("name"));
                                authorsToAdd.add(author);
                            });
                            preference.setFavouriteAuthors(authorsToAdd);
                        }
                        break;

                    default:
                        throw new PreferenceNotFoundException("Unable to edit preference");
                }
            }
        });
        return preferenceRepository.save(preference);
    }
}
