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
//        preference.setAppUser(appUser);
//        preference.setFavouriteGenres(genres);
//        preference.setFavouriteAuthors(authors);
        return preferenceRepository.save(preference);
    }
}
