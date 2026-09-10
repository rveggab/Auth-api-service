package io.github.rveggab.auth.users.app.ports.input;

import io.github.rveggab.auth.users.domain.model.User;
import io.github.rveggab.auth.users.infrastructure.rest.response.UserResponse;

import java.util.List;
import java.util.UUID;

public interface FindUserServicePort {
    UserResponse executeById(UUID id);

    User executeByName(String name);

    List<User> execute();

}
