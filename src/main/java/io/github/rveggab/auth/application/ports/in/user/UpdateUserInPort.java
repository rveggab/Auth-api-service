package io.github.rveggab.auth.application.ports.in.user;

import io.github.rveggab.auth.domain.model.identity.User;
import io.github.rveggab.auth.infrastructure.adapter.in.web.dto.request.user.UpdateUserAdminRequest;
import io.github.rveggab.auth.infrastructure.adapter.in.web.dto.request.user.UpdateUserRequest;

public interface UpdateUserInPort {
    void updateProfile(Long id, UpdateUserRequest updateUserRequest);
    void updatePermission(Long actor, Long id, UpdateUserAdminRequest adminRequest);
    void delete(Long id);
}
