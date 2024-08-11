package com.readerspath.backend.model;

import java.util.Date;

public record LoginResponse(
        String token,
        String role,
        Date expiration
) {
}
