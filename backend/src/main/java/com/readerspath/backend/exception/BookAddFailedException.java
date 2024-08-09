package com.readerspath.backend.exception;

public class BookAddFailedException extends RuntimeException {
    public BookAddFailedException() {
    }

    public BookAddFailedException(String message) {
        super(message);
    }
}
