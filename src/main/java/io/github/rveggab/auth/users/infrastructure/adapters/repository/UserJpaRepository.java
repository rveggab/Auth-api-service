package io.github.rveggab.auth.users.infrastructure.adapters.repository;

import io.github.rveggab.auth.users.infrastructure.adapters.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByName(String name);
}
