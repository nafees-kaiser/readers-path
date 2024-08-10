package com.readerspath.backend.projection;

import com.readerspath.backend.enums.ShelfState;

public interface ShelfView {
    Long getId();

    BookView getBook();

    AppUserView getAppUser();

    ShelfState getState();
}
