package io.github.rveggab.auth.application.ports.in.login;

import io.github.rveggab.auth.domain.model.identity.User;

public interface LoginUserInPort {
    User execute(String username, String password);
}
