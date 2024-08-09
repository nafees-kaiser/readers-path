package com.readerspath.backend.exception;

public class QuestionariesNotFoundException extends RuntimeException {
    public QuestionariesNotFoundException() {
    }

    public QuestionariesNotFoundException(String message) {
        super(message);
    }
}
