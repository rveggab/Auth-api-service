package io.github.rveggab.auth.application.ports.in;

import io.github.rveggab.auth.domain.model.identity.User;

import java.util.List;

public interface FindUserInPort {
    User executeWithId(Long id);
    User executeWithEmail(String email);
    User executeWithUsername(String username);
    List<User> execute();
}
