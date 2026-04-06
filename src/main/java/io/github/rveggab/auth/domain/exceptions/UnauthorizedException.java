package io.github.rveggab.auth.domain.exceptions;

public class UnauthorizedException extends AuthException{
    public UnauthorizedException(String message){super(message, 4003);}
}
