package com.readerspath.backend.service;

import com.readerspath.backend.model.AppUser;
import com.readerspath.backend.model.Shelf;

import java.util.List;

public interface ShelfService {
    Shelf addToShelf(Shelf shelf);

    List<Shelf> findShelfByAppUser(AppUser appUser);

    List<Shelf> getShelf();

    Shelf findShelfById(Long id);

    Shelf changeShelfState(Shelf shelf);

    void deleteShelf(Long id);
}
