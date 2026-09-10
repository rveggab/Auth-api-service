package io.github.rveggab.auth.users.app.ports.input;

import io.github.rveggab.auth.users.domain.model.User;
import io.github.rveggab.auth.users.infrastructure.rest.request.UserRequest;

import java.util.UUID;

public interface WriteUserServicePort {
    User execute(UserRequest request);

    void delete(UUID id);
}
