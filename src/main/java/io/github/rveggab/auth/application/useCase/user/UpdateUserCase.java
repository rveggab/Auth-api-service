package io.github.rveggab.auth.application.useCase.user;

import io.github.rveggab.auth.application.ports.in.user.UpdateUserInPort;
import io.github.rveggab.auth.application.ports.out.UserRepositoryOutPort;
import io.github.rveggab.auth.domain.exceptions.EntityNotFoundException;
import io.github.rveggab.auth.domain.exceptions.InvalidDataException;
import io.github.rveggab.auth.domain.exceptions.UnauthorizedException;
import io.github.rveggab.auth.domain.model.enums.UserStatus;
import io.github.rveggab.auth.domain.model.identity.User;
import io.github.rveggab.auth.infrastructure.adapter.in.web.dto.request.user.UpdateUserAdminRequest;
import io.github.rveggab.auth.infrastructure.adapter.in.web.dto.request.user.UpdateUserRequest;
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
        User user = outPort.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));

        // update to username
        if (updateUserRequest.getUsername() != null && !updateUserRequest.getUsername().isEmpty())
            user.changeUsername(updateUserRequest.getUsername());

        // update to email
        if (updateUserRequest.getEmail() != null) user.changeEmail(updateUserRequest.getEmail());

        // Update to password
        if (updateUserRequest.getPassword() != null) {
            if (!updateUserRequest.getPassword().equals(updateUserRequest.getConfirmPassword())) {
                throw new InvalidDataException("Passwords do not match");
            }
            user.changePassword(updateUserRequest.getPassword(), passwordEncoder);
        }

        outPort.save(user);
    }

    @Override
    public void updatePermission(Long actor, Long id, UpdateUserAdminRequest adminRequest) {
        User admin = outPort.findById(actor).orElseThrow(() -> new EntityNotFoundException("Actor not found"));

        if (!admin.isAdmin())
            throw new UnauthorizedException("Does not have permission to make this change");

        User user = outPort.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (adminRequest.getStatus() != null) {
            user.changeStatus(adminRequest.getStatus());
        }

        if (adminRequest.getRol() != null) {
            user.changeRole(adminRequest.getRol());
        }

        outPort.save(user);
    }

    @Override
    public void delete(Long id) {
        User user = outPort.findById(id).orElseThrow(() -> new EntityNotFoundException("Cannot delete this user or not exist"));

        user.changeStatus(UserStatus.I);

        outPort.save(user);
    }
}
