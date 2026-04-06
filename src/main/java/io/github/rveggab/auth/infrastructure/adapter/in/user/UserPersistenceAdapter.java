package io.github.rveggab.auth.infrastructure.adapter.in.user;

import io.github.rveggab.auth.application.ports.out.UserRepositoryOutPort;
import io.github.rveggab.auth.domain.model.identity.User;
import io.github.rveggab.auth.infrastructure.adapter.mapper.user.UserMapper;
import io.github.rveggab.auth.infrastructure.adapter.out.persistence.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserRepositoryOutPort {

    private final JpaUserRepository userAdapter;

    @Override
    public Optional<User> findById(Long id) {
        return userAdapter.findById(id).map(UserMapper::toDomain);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userAdapter.findByUsername(username).map(UserMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userAdapter.findByEmail(email).map(UserMapper::toDomain);
    }

    @Override
    public List<User> findAll() {
        return userAdapter.findAll().stream()
                .map(UserMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public User save(User user) {
        UserEntity entity = UserMapper.toEntity(user);
        UserEntity saveEntity = userAdapter.save(entity);
        return UserMapper.toDomain(saveEntity);
    }

}
