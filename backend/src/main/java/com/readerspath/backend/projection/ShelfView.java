package com.readerspath.backend.projection;

import com.readerspath.backend.enums.ShelfState;

import java.util.List;

public interface ShelfView {
    Long getId();

    List<BookView> getBooks();

    AppUserView getAppUser();

    ShelfState getState();
}
