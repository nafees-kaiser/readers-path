package com.readerspath.backend.repository;

import com.readerspath.backend.model.BookFilterReq;
import com.readerspath.backend.projection.BookView;
import org.springframework.data.domain.Page;

public interface BookFilterCustomRepository {
    Page<BookView> filterBooks(BookFilterReq req);
}
