package io.github.rveggab.auth.domain.exceptions;

public class EntityAlreadyExistsException extends AuthException{
    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
