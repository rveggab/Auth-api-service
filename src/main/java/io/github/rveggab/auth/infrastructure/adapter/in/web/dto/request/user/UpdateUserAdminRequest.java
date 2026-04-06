package io.github.rveggab.auth.infrastructure.adapter.in.web.dto.request.user;

import io.github.rveggab.auth.domain.model.enums.UserRoles;
import io.github.rveggab.auth.domain.model.enums.UserStatus;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class UpdateUserAdminRequest {
    @Parameter(name = "user admin", description = "who request to change a permit", required = true)
    @NonNull
    private Long idActor;
    @Parameter(name = "new status", description = "change for new user status")
    private UserStatus status;
    @Parameter(name = "new role", description = "change for new user role")
    private UserRoles rol;
}
