package io.github.rveggab.auth.application.ports.in;

import io.github.rveggab.auth.domain.model.identity.User;

public interface ChangePassUserInPort {
    User execute(String oldPassword, String newPassword);
}
