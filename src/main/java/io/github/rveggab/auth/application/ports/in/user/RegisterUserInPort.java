package io.github.rveggab.auth.application.ports.in.user;

import io.github.rveggab.auth.domain.model.identity.User;

public interface RegisterUserInPort {
    User execute(User user, String passwordConfirm);
}
