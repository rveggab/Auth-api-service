package io.github.rveggab.auth.users.app.service;

import io.github.rveggab.auth.users.app.ports.input.WriteUserServicePort;
import io.github.rveggab.auth.users.app.ports.output.UserPersistencePort;
import io.github.rveggab.auth.users.domain.exception.InvalidUserException;
import io.github.rveggab.auth.users.domain.exception.NotFoundUserException;
import io.github.rveggab.auth.users.domain.exception.UserExeption;
import io.github.rveggab.auth.users.domain.model.User;
import io.github.rveggab.auth.users.domain.model.UserStatus;
import io.github.rveggab.auth.users.domain.valueObjects.PersonName;
import io.github.rveggab.auth.users.infrastructure.rest.request.UserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class WriteUserService implements WriteUserServicePort {

    private final UserPersistencePort userPort;

    @Override
    public User execute(UserRequest request) {
        try {
            var name = new PersonName(request.name());
            var paternal = new PersonName(request.paternalSurname());
            var maternal = new PersonName(request.maternalSurname());

            User user = User.create(
                    name.value(),
                    paternal.value(),
                    maternal.value()
            );

            var save = userPort.save(user);
            log.info("User Was created successfully");

            return save;
        } catch (UserExeption ex) {
            log.error("Error creating a new user", ex);
            throw ex;
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            User user = userPort.findById(id)
                    .orElseThrow(() -> new NotFoundUserException("Cannot delete this user or not exists"));

            if (user.getStatus() == UserStatus.I)
                throw new InvalidUserException("This user is inactive");

            user.setStatus(UserStatus.I);
            userPort.save(user);

            log.info("User was deleted successfully");
        } catch (UserExeption ex) {
            log.error("Cannot delete this user", ex);
            throw ex;
        }
    }
}
