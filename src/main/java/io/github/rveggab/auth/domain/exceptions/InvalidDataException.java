package io.github.rveggab.auth.domain.exceptions;

public class InvalidDataException extends AuthException{
    public InvalidDataException(String message) {
        super(message);
    }
}
