package io.github.rveggab.auth.users.app.service;

import io.github.rveggab.auth.users.app.mapper.UserMapper;
import io.github.rveggab.auth.users.app.ports.input.FindUserServicePort;
import io.github.rveggab.auth.users.app.ports.output.UserPersistencePort;
import io.github.rveggab.auth.users.domain.exception.NotFoundUserException;
import io.github.rveggab.auth.users.domain.model.User;
import io.github.rveggab.auth.users.infrastructure.rest.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindUserService implements FindUserServicePort {

    private final UserPersistencePort userPort;
    private final UserMapper userMapper;

    @Override
    public UserResponse executeById(UUID id) {
        User user = userPort.findById(id)
                .orElseThrow(() -> new NotFoundUserException("This user may not exist"));

        if(!user.isActive()) throw new NotFoundUserException("This user not found");

        return userMapper.toUserResponse(user);
    }

    @Override
    public User executeByName(String name) {
        User user = userPort.findByName(name)
                .orElseThrow(() -> new NotFoundUserException("This user not found"));

        if(!user.isActive()) throw new NotFoundUserException("This user not found");

        return user;
    }

    @Override
    public List<User> execute() {
        return userPort.findAll();
    }
}
