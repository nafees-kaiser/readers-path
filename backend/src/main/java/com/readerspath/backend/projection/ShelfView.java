package com.readerspath.backend.projection;

import java.util.List;

public interface ShelfView {
    Long getId();

    List<BookView> getBooks();

    AppUserView getAppUser();
}
