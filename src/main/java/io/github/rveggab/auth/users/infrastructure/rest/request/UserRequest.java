package io.github.rveggab.auth.users.infrastructure.rest.request;

import jakarta.validation.constraints.NotBlank;

public record UserRequest(
        @NotBlank(message = "The name is required")
        String name,

        @NotBlank(message = "The paternal name is required")
        String paternalSurname,

        @NotBlank(message = "The maternal name is required")
        String maternalSurname
) {
}
