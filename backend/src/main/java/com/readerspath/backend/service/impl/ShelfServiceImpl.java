package com.readerspath.backend.service.impl;

import com.readerspath.backend.enums.ShelfState;
import com.readerspath.backend.exception.BookNotFoundException;
import com.readerspath.backend.model.AppUser;
import com.readerspath.backend.model.Book;
import com.readerspath.backend.model.Shelf;
import com.readerspath.backend.repository.ShelfRepository;
import com.readerspath.backend.service.AppUserService;
import com.readerspath.backend.service.BookService;
import com.readerspath.backend.service.ShelfService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShelfServiceImpl implements ShelfService {
    @Autowired
    private ShelfRepository shelfRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private AppUserService appUserService;

    @Override
    public Shelf addToShelf(Shelf shelf) {
        AppUser appUser = appUserService.getAppUserFromSession();
        Book book = bookService.findBookById(shelf.getBook().getId());
        shelf.setBook(book);
        shelf.setAppUser(appUser);
        shelf.setState(ShelfState.WISH_TO_READ);
        return shelfRepository.save(shelf);
    }

    @Override
    public List<Shelf> findShelfByAppUser(AppUser appUser) {
        return shelfRepository.findAllByAppUser(appUser);
    }

    @Transactional
    @Override
    public List<Shelf> getShelf() {
        AppUser appUser = appUserService.getAppUserFromSession();
        return this.findShelfByAppUser(appUser);
    }

    @Override
    public Shelf findShelfById(Long id) {
        return shelfRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("The book is not added to shelf"));
    }

    @Override
    public Shelf changeShelfState(Shelf shelf) {
        Shelf toUpdateShelf = this.findShelfById(shelf.getId());
        toUpdateShelf.setState(shelf.getState());
        return shelfRepository.save(toUpdateShelf);
    }

    @Override
    public void deleteShelf(Long id) {
//        Shelf shelf = this.findShelfById(id);
        Book book = bookService.findBookById(id);
        AppUser appUser = appUserService.getAppUserFromSession();
        Shelf shelf = shelfRepository.findByAppUserAndBook(appUser, book);
        shelfRepository.delete(shelf);
    }

    @Override
    public Boolean bookExists(Long id) {
        Book book = bookService.findBookById(id);
        AppUser appUser = appUserService.getAppUserFromSession();
        Shelf shelf = shelfRepository.findByAppUserAndBook(appUser, book);
        return shelf != null;
    }


}
