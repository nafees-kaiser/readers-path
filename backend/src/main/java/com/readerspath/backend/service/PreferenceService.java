package com.readerspath.backend.service;

import com.readerspath.backend.model.AppUser;
import com.readerspath.backend.model.Author;
import com.readerspath.backend.model.Category;
import com.readerspath.backend.model.Preference;

import java.util.List;
import java.util.Map;

public interface PreferenceService {

    Preference register(Preference preference);

    Preference addPreference(AppUser appUser, List<Category> categories, List<Author> authors);

    //    Preference editPreference(Preference preference, AppUser appUser);
    Preference editPreference(Map<String, Object> updates);

    Preference findPreferenceByAppUser(AppUser appUser);

    Preference getPreference();
}
