package io.github.rveggab.auth.users.domain.valueObjects;

import io.github.rveggab.auth.users.domain.exception.InvalidUserException;

public record PersonName(String value) {
    public PersonName{
        if(value == null || value.isBlank())
            throw new InvalidUserException("All fields are required");

        if(value.length() > 100)
            throw new InvalidUserException("Some field exceed the characters limit");

        if(!value.matches("^\\p{L}+(\\s+\\p{L}+)*$"))
            throw new InvalidUserException("Some value contains invalid characters");
    }
}
