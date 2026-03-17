package io.github.rveggab.auth.application.useCase;

import io.github.rveggab.auth.application.ports.in.RegisterUserInPort;
import io.github.rveggab.auth.application.ports.out.UserRepositoryOutPort;
import io.github.rveggab.auth.domain.exceptions.EntityAlreadyExistsException;
import io.github.rveggab.auth.domain.exceptions.InvalidDataException;
import io.github.rveggab.auth.domain.model.enums.UserRoles;
import io.github.rveggab.auth.domain.model.enums.UserStatus;
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

        validateUserRegis(user);

        if (!user.getPassword().equals(passwordConfirm))
            throw new InvalidDataException("Las contraseñas no coinciden");

        if (outPort.findByEmail(user.getEmail()).isPresent())
            throw new EntityAlreadyExistsException("Este email ya esta en uso");

        if (user.getRole() == null)
            user.setRole(UserRoles.U);

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        user.setStatus(UserStatus.A);
        return outPort.save(user);
    }

    private void validateUserRegis(User user) {
        if (user.getPassword() == null || user.getPassword().length() < 12)
            throw new InvalidDataException("La contraseña debe tener al menos 12 caracteres.");

        if (user.getEmail() == null || !user.getEmail().contains("@"))
            throw new InvalidDataException("Formato de email inválido.");

    }
}
