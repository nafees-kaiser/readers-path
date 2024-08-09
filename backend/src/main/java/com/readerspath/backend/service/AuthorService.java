package com.readerspath.backend.service;

import com.readerspath.backend.model.Author;

public interface AuthorService {
    Author addAuthor(Author author);

    Author findAuthorByName(String name);
}
