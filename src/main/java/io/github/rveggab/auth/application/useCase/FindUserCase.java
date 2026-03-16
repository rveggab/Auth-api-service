package io.github.rveggab.auth.application.useCase;

import io.github.rveggab.auth.application.ports.in.FindUserInPort;
import io.github.rveggab.auth.application.ports.out.UserRepositoryOutPort;
import io.github.rveggab.auth.domain.exceptions.EntityNotFoundException;
import io.github.rveggab.auth.domain.model.identity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindUserCase implements FindUserInPort {

    private final UserRepositoryOutPort outPort;

    @Override
    public User executeWithId(Long id) {
        return outPort.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("This user not found"));
    }

    @Override
    public User executeWithEmail(String email) {
        return outPort.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("This email address not found"));
    }

    @Override
    public User executeWithUsername(String username) {
        return outPort.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("This username is not registered"));
    }

    @Override
    public List<User> execute() {
        return outPort.findAll();
    }
}
