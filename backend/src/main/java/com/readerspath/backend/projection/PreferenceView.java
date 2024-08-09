package com.readerspath.backend.projection;

import com.readerspath.backend.model.Author;
import com.readerspath.backend.model.Category;

import java.util.List;

public interface PreferenceView {
    Long getId();
    AppUserView getAppUser();
    List<Category> getFavouriteCategories();
    List<Author> getFavouriteAuthors();
}
