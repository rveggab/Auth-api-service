package io.github.rveggab.auth.infrastructure.adapter.in.web.dto.request.user;

import io.github.rveggab.auth.infrastructure.utils.validations.passwords.ValidPassword;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {
    @Parameter(name = "name", description = "The user's name", required = true)
    @NotBlank
    @Size(max = 60)
    private String name;
    @Parameter(name = "middleName", description = "The first lastname", required = true)
    @NotBlank
    @Size(max = 60)
    private String middleName;
    @Parameter(name = "lastname", description = "The second lastname", required = true)
    @NotBlank
    @Size(max = 60)
    private String lastName;
    @Parameter(name = "username", description = "An alias for username", required = true)
    @NotBlank
    @Size(min = 3, max = 50)
    private String username;
    @Parameter(name = "email", description = "The email address from user", required = true)
    @NotBlank
    @Email
    private String email;
    @Parameter(name = "password", description = "The password assigned from user", required = true)
    @NotBlank
    @Size(min = 12)
    @ValidPassword(message = "This password does not match the security rules")
    private String password;
    @Parameter(name = "confirmed", description = "The password confirmed", required = true)
    @NotBlank
    private String passwordConfirmation;
}
