package com.readerspath.backend.service;

import com.readerspath.backend.model.AppUser;
import com.readerspath.backend.model.Shelf;

import java.util.List;

public interface ShelfService {
    Shelf addToShelf(Shelf shelf, String email);

    List<Shelf> findShelfByAppUser(AppUser appUser);

    List<Shelf> getShelf(String email);

    Shelf findShelfById(Long id);

    Shelf changeShelfState(Shelf shelf, String email);

    void deleteShelf(Long id);
}
