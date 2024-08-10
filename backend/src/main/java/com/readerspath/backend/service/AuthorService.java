package com.readerspath.backend.service;

import com.readerspath.backend.model.AppUser;
import com.readerspath.backend.model.Author;

import java.util.List;

public interface AuthorService {
    Author addAuthor(Author author);

    Author findAuthorByName(String name);

    Author findAuthorByAppUser(AppUser appUser);

    List<Author> getAuthors();
}
