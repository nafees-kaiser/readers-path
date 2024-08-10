package com.readerspath.backend.service;

import com.readerspath.backend.model.AppUser;
import com.readerspath.backend.model.Author;
import com.readerspath.backend.model.Category;
import com.readerspath.backend.model.Preference;

import java.util.List;

public interface PreferenceService {

    Preference addPreference(AppUser appUser, List<Category> categories, List<Author> authors);

    //    Preference editPreference(Preference preference, AppUser appUser);
    Preference editPreference(AppUser appUser, List<Category> categories, List<Author> authors);

    Preference findPreferenceByAppUser(AppUser appUser);
}
