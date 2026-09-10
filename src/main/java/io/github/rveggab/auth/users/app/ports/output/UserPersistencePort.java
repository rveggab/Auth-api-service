package io.github.rveggab.auth.users.app.ports.output;

import io.github.rveggab.auth.users.domain.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserPersistencePort {
    Optional<User> findById(UUID id);
    Optional<User> findByName(String name);
    List<User> findAll();
    User save(User user);
    void deleteById(UUID id);
}
