package com.readerspath.backend.exception;

public class PreferenceNotFoundException extends RuntimeException {
    public PreferenceNotFoundException() {
    }

    public PreferenceNotFoundException(String message) {
        super(message);
    }
}
