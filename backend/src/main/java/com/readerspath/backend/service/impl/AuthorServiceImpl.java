package com.readerspath.backend.service.impl;

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
    public String addAuthor(Author author){
        authorRepository.save(author);
        return "Author created successfully";
    }

    @Override
    public Author findAuthorByName(String name){
        return authorRepository.findByName(name);
    }
}
