package io.github.rveggab.auth.infrastructure.adapter.in.web.dto.request.user;

import io.github.rveggab.auth.infrastructure.utils.validations.passwords.ValidPassword;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateUserRequest {
    @Parameter(name = "new email address", description = "The new email address")
    @Email
    private String email;
    @Parameter(name = "new username", description = "the new username from user")
    @Size(min = 3, max = 50)
    private String username;
    @Parameter(name = "new password", description = "the new password assigned from user")
    @Size(min = 12)
    @ValidPassword(message = "This password does not match the security rules")
    private String password;
    @Parameter(name = "confirmed password", description = "The password confirmed")
    private String confirmPassword;
}
