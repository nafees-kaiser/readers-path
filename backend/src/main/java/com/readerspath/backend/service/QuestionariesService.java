package com.readerspath.backend.service;

import com.readerspath.backend.model.Questionaries;

public interface QuestionariesService {
    Questionaries addQuestionaries(Questionaries questionaries, String email);

    Questionaries addAnswers(Questionaries questionaries, String email);

    Questionaries findById(Long id);
}
