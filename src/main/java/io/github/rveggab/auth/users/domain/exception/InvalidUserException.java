package io.github.rveggab.auth.users.domain.exception;

public class InvalidUserException extends UserExeption {
    public InvalidUserException(String message) {
        super(message, 400);
    }
}
