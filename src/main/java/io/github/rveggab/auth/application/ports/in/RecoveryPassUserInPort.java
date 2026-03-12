package io.github.rveggab.auth.application.ports.in;

import io.github.rveggab.auth.domain.model.identity.User;

public interface RecoveryPassUserInPort {
    User execute(String email);
}
