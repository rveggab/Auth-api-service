package io.github.rveggab.auth.domain.exceptions;

public class EntityNotFoundException extends AuthException {
    public EntityNotFoundException(String message) {
        super(message, 404);
    }
}
