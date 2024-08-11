package com.readerspath.backend.service.impl;

import com.readerspath.backend.exception.QuestionariesNotFoundException;
import com.readerspath.backend.model.AppUser;
import com.readerspath.backend.model.Book;
import com.readerspath.backend.model.Questionaries;
import com.readerspath.backend.repository.QuestionariesRepository;
import com.readerspath.backend.service.AppUserService;
import com.readerspath.backend.service.BookService;
import com.readerspath.backend.service.QuestionariesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionariesServiceImpl implements QuestionariesService {
    @Autowired
    private QuestionariesRepository questionariesRepository;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private BookService bookService;

    @Override
    public Questionaries addQuestionaries(Questionaries questionaries) {
        AppUser appUser = appUserService.getAppUserFromSession();
        questionaries.setAskedBy(appUser);
        Book book = bookService.findBookById(questionaries.getBook().getId());
        questionaries.setBook(book);
        return questionariesRepository.save(questionaries);
    }

    @Override
    public Questionaries addAnswers(Questionaries questionaries) {
        Questionaries newQuestionaries = this.findById(questionaries.getId());
        newQuestionaries.setAnswer(questionaries.getAnswer());
        return questionariesRepository.save(newQuestionaries);
    }

    @Override
    public Questionaries findById(Long id) {
        return questionariesRepository.findById(id).orElseThrow(() -> new QuestionariesNotFoundException("Question does not exist"));
    }
}
