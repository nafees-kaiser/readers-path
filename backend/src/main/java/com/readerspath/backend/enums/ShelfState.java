package com.readerspath.backend.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ShelfState {
    WISH_TO_READ("Wish to read"),
    ON_PROGRESS("On progress"),
    FINISHED("Finished");

    private final String state;

}
