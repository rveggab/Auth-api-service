package io.github.rveggab.auth.application.ports.out;

import io.github.rveggab.auth.domain.model.identity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryOutPort {
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    List<User> findAll();
    User save(User user);
}
