package com.readerspath.backend.model;

import java.util.List;

public record BookFilterReq(
        String search,
        List<String> category,
        String sortBy,
        int pageNumber
) {
}
