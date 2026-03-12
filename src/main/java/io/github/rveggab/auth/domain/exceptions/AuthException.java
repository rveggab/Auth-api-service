package io.github.rveggab.auth.domain.exceptions;

public abstract class AuthException extends RuntimeException {
    protected AuthException(String message){
        super(message);
    }
}
