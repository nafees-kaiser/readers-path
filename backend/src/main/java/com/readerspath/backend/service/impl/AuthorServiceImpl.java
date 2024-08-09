package com.readerspath.backend.service.impl;

import com.readerspath.backend.model.AppUser;
import com.readerspath.backend.model.Author;
import com.readerspath.backend.repository.AuthorRepository;
import com.readerspath.backend.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author findAuthorByName(String name) {
        return authorRepository.findByName(name);
    }

    @Override
    public Author findAuthorByAppUser(AppUser appUser) {
        return authorRepository.findByAppUser(appUser);
    }
}
