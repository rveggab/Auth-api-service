package io.github.rveggab.auth.infrastructure.adapter.in.web.dto.request;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {
    @Parameter(name = "name", description = "The user's name", required = true)
    private String name;
    @Parameter(name = "middleName", description = "The first lastname", required = true)
    private String middleName;
    @Parameter(name = "lastname", description = "The second lastname", required = true)
    private String lastName;
    @Parameter(name = "username", description = "An alias for username", required = true)
    private String username;
    @Parameter(name = "email", description = "The email address from user", required = true)
    private String email;
    @Parameter(name = "password", description = "The password assigned from user", required = true)
    private String password;
    @Parameter(name = "confirmed", description = "The password confirmed", required = true)
    private String passwordConfirmation;
}
