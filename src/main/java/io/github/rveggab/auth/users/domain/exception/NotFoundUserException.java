package io.github.rveggab.auth.users.domain.exception;

public class NotFoundUserException extends UserExeption{
    public NotFoundUserException(String message){
        super(message, 404);
    }
}
