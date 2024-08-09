package com.readerspath.backend.service.impl;

import com.readerspath.backend.model.AppUser;
import com.readerspath.backend.model.Book;
import com.readerspath.backend.model.Shelf;
import com.readerspath.backend.repository.ShelfRepository;
import com.readerspath.backend.service.AppUserService;
import com.readerspath.backend.service.BookService;
import com.readerspath.backend.service.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShelfServiceImpl implements ShelfService {
    @Autowired
    private ShelfRepository shelfRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private AppUserService appUserService;

    @Override
    public Shelf addToShelf(Book book, String email) {
        AppUser appUser = appUserService.getAppUserByEmail(email);
        Shelf shelf = this.findShelfByAppUser(appUser);
        if (shelf == null) {
            shelf = new Shelf();
            shelf.setAppUser(appUser);
        }
        book = bookService.findBookById(book.getId());
        book = bookService.setState(book);
        shelf.setBooks(book);
//        shelf.setState(ShelfState.WISH_TO_READ);
        return shelfRepository.save(shelf);
    }

    @Override
    public Shelf findShelfByAppUser(AppUser appUser) {
        return shelfRepository.findByAppUser(appUser);
    }
}
