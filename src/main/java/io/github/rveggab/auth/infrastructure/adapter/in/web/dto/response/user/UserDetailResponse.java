package io.github.rveggab.auth.infrastructure.adapter.in.web.dto.response.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDetailResponse {
    private String name;
    private String middleName;
    private String lastName;
    private String email;
    private String status;
}
