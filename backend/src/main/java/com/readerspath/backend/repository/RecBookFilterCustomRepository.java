package com.readerspath.backend.repository;

import com.readerspath.backend.model.AppUser;
import com.readerspath.backend.model.BookFilterReq;
import com.readerspath.backend.projection.BookView;
import org.springframework.data.domain.Page;

public interface RecBookFilterCustomRepository {

    Page<BookView> filterBooks(BookFilterReq req, AppUser appUser);
}
