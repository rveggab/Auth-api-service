package io.github.rveggab.auth.domain.model.identity;

import io.github.rveggab.auth.domain.model.enums.UserRoles;
import io.github.rveggab.auth.domain.model.enums.UserStatus;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    private Long id;
    private String name;
    private String middleName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private UserRoles role;
    private UserStatus status;

    // isActive?
    public boolean isActive() {
        return status.canAuth();
    }

    // isAdmin?
    public boolean isAdmin() {
        return role == UserRoles.A;
    }
}
