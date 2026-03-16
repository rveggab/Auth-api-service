package io.github.rveggab.auth.application.useCase;

import io.github.rveggab.auth.application.ports.in.UpdateUserInPort;
import io.github.rveggab.auth.application.ports.out.UserRepositoryOutPort;
import io.github.rveggab.auth.domain.exceptions.EntityNotFoundException;
import io.github.rveggab.auth.domain.exceptions.InvalidDataException;
import io.github.rveggab.auth.domain.model.enums.UserStatus;
import io.github.rveggab.auth.domain.model.identity.User;
import io.github.rveggab.auth.infrastructure.adapter.in.web.dto.request.UpdateUserAdminRequest;
import io.github.rveggab.auth.infrastructure.adapter.in.web.dto.request.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserCase implements UpdateUserInPort {
    private final UserRepositoryOutPort outPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void updateProfile(Long id, UpdateUserRequest updateUserRequest) {
        User user = outPort.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (updateUserRequest.getUsername() != null && !updateUserRequest.getUsername().isEmpty())
            user.setUserName(updateUserRequest.getUsername());

        if (updateUserRequest.getEmail() != null && updateUserRequest.getEmail().contains("@"))
            user.setEmail(updateUserRequest.getEmail());

        if (updateUserRequest.getPassword() != null && updateUserRequest.getPassword().length() >= 12) {
            if (!updateUserRequest.getPassword().equals(updateUserRequest.getConfirmPassword())) {
                throw new InvalidDataException("Passwords do not match");
            }
            String encoded = passwordEncoder.encode(updateUserRequest.getPassword());
            user.setPassword(encoded);
        }
        outPort.save(user);
    }

    @Override
    public void updatePermission(Long id, UpdateUserAdminRequest adminRequest) {
        User user = outPort.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (adminRequest.getStatus() != null) {
            user.setStatus(adminRequest.getStatus());
        }

        if (adminRequest.getRol() != null) {
            user.setRole(adminRequest.getRol());
        }

        outPort.save(user);
    }

    @Override
    public void delete(Long id) {
        User user = outPort.findById(id).orElseThrow(() -> new EntityNotFoundException("Cannot delete this user or not exist"));

        user.setStatus(UserStatus.I);

        outPort.save(user);
    }
}
