package io.github.rveggab.auth.infrastructure.adapter.in.web.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateUserRequest {
    private String email;
    private String username;
    private String password;
    private String confirmPassword;
}
