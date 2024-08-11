package com.readerspath.backend.repository;

import com.readerspath.backend.model.Book;
import com.readerspath.backend.model.BookFilterReq;

import java.util.List;

public interface BookFilterCustomRepository {
    List<Book> filterBooks(BookFilterReq req);
}
