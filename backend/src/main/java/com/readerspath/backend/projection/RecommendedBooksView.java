package com.readerspath.backend.projection;

import java.util.List;

public interface RecommendedBooksView {
    Long getId();

    List<BookView> getBooks();

    AppUserView getAppUser();
}
