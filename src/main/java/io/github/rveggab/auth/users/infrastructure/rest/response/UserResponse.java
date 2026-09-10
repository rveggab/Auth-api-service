package io.github.rveggab.auth.users.infrastructure.rest.response;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String paternalSurname,
        String maternalSurname
) {}
