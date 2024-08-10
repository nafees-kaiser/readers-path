package com.readerspath.backend.service.impl;

import com.readerspath.backend.model.AppUser;
import com.readerspath.backend.model.Author;
import com.readerspath.backend.model.Category;
import com.readerspath.backend.model.Preference;
import com.readerspath.backend.repository.PreferenceRepository;
import com.readerspath.backend.service.PreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreferenceServiceImpl implements PreferenceService {
    @Autowired
    private PreferenceRepository preferenceRepository;

    @Override
    public Preference addPreference(AppUser appUser, List<Category> categories, List<Author> authors) {
        Preference preference = new Preference(appUser, categories, authors);
        return preferenceRepository.save(preference);
    }

    @Override
    public Preference editPreference(AppUser appUser, List<Category> categories, List<Author> authors) {
        Preference newPreference = preferenceRepository.findByAppUser(appUser);
        if (categories != null) {
            newPreference.setFavouriteCategories(categories);
        }

        if (authors != null) {
            newPreference.setFavouriteAuthors(authors);
        }

        try {
            return preferenceRepository.save(newPreference);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
//        newPreference = preferenceRepository.save(newPreference);
//        return newPreference;

    }

    @Override
    public Preference findPreferenceByAppUser(AppUser appUser) {
        return preferenceRepository.findByAppUser(appUser);
    }
}
