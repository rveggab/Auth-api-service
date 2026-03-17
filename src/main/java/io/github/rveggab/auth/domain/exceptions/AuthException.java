package io.github.rveggab.auth.domain.exceptions;

import lombok.Getter;

@Getter
public abstract class AuthException extends RuntimeException {
    private final int statusCode;

    protected AuthException(String message, int status){
        super(message);
        this.statusCode = status;
    }

}
