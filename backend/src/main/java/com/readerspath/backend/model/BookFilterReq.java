package com.readerspath.backend.model;

public record BookFilterReq(
        String search,
        String category,
        String sortBy
) {
}
