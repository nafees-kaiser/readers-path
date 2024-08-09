package com.readerspath.backend.service;

import com.readerspath.backend.model.AppUser;
import com.readerspath.backend.model.Book;
import com.readerspath.backend.model.Shelf;

public interface ShelfService {
    Shelf addToShelf(Book book, String email);

    Shelf findShelfByAppUser(AppUser appUser);

    Shelf getShelf(String email);

    Shelf changeShelfState(Book book, String email);
}
