package io.github.rveggab.auth.users.domain.exception;


public abstract class UserExeption extends RuntimeException {
    private int status;

    protected UserExeption(String message, int status){
        super(message);
        this.status = status;
    }

    protected UserExeption() {}
}
