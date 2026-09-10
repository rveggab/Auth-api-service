package io.github.rveggab.auth.users.infrastructure.adapters.persistence;

import io.github.rveggab.auth.users.app.ports.output.UserPersistencePort;
import io.github.rveggab.auth.users.domain.exception.NotFoundUserException;
import io.github.rveggab.auth.users.domain.model.User;
import io.github.rveggab.auth.users.infrastructure.adapters.entity.UserEntity;
import io.github.rveggab.auth.users.infrastructure.adapters.mapper.UserJpaMapper;
import io.github.rveggab.auth.users.infrastructure.adapters.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserJpaAdapter implements UserPersistencePort {

    private final UserJpaRepository repository;
    private final UserJpaMapper mapper;

    @Override
    public Optional<User> findById(UUID id) {

        return repository.findById(id)
                .map(mapper::toUser);
    }

    @Override
    public Optional<User> findByName(String name) {
        return repository.findByName(name)
                .map(mapper::toUser);
    }

    @Override
    public List<User> findAll() {
        return mapper.toUserList(repository.findAll());
    }

    @Override
    public User save(User user) {
        UserEntity entity = mapper.toUserEntity(user);

        return mapper.toUser(repository.save(entity));
    }

    @Override
    public void deleteById(UUID id) {
        if(repository.findById(id).isEmpty()) throw new NotFoundUserException("This user could be not exists!");

        repository.deleteById(id);
    }
}
