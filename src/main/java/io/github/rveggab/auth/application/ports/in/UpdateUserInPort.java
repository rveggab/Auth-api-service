package io.github.rveggab.auth.application.ports.in;

import io.github.rveggab.auth.infrastructure.adapter.in.web.dto.request.UpdateUserAdminRequest;
import io.github.rveggab.auth.infrastructure.adapter.in.web.dto.request.UpdateUserRequest;

public interface UpdateUserInPort {
    void updateProfile(Long id, UpdateUserRequest updateUserRequest);
    void updatePermission(Long id, UpdateUserAdminRequest adminRequest);
    void delete(Long id);
}
