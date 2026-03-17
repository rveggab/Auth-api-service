package io.github.rveggab.auth.infrastructure.adapter.in.web.dto.request;

import io.github.rveggab.auth.domain.model.enums.UserRoles;
import io.github.rveggab.auth.domain.model.enums.UserStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateUserAdminRequest {
    private UserStatus status;
    private UserRoles rol;
}
