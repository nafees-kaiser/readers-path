package com.readerspath.backend.service;

import com.readerspath.backend.model.Questionaries;

public interface QuestionariesService {
    Questionaries addQuestionaries(Questionaries questionaries);

    Questionaries addAnswers(Questionaries questionaries);

    Questionaries findById(Long id);
}
