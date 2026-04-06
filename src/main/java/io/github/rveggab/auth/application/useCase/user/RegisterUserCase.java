package io.github.rveggab.auth.application.useCase.user;

import io.github.rveggab.auth.application.ports.in.user.RegisterUserInPort;
import io.github.rveggab.auth.application.ports.out.UserRepositoryOutPort;
import io.github.rveggab.auth.domain.exceptions.EntityAlreadyExistsException;
import io.github.rveggab.auth.domain.exceptions.InvalidDataException;
import io.github.rveggab.auth.domain.model.identity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegisterUserCase implements RegisterUserInPort {
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserRepositoryOutPort outPort;

    @Override
    public User execute(User user, String passwordConfirm) {

        if (!user.getPassword().equals(passwordConfirm))
            throw new InvalidDataException("The password's does not match");

        if (outPort.findByEmail(user.getEmail()).isPresent())
            throw new EntityAlreadyExistsException("This email already exists");

        user.assignDefaultRole();

        user.encoderPass(passwordEncoder);

        user.activateUser();
        return outPort.save(user);
    }
}
