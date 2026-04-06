package io.github.rveggab.auth.domain.model.identity;

import io.github.rveggab.auth.domain.model.enums.UserRoles;
import io.github.rveggab.auth.domain.model.enums.UserStatus;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
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

    public void assignDefaultRole(){
        if (this.role == null)
            role = UserRoles.U;
    }

    public void activateUser(){
        this.status = UserStatus.A;
    }

    public void encoderPass(PasswordEncoder encoder){
        this.password = encoder.encode(this.password);
    }

    public void changePassword(String rawPassword, PasswordEncoder encoder){
        this.password = encoder.encode(rawPassword);
    }

    public void changeEmail(String email){
        this.email = email;
    }

    public void changeUsername(String username){
        this.userName = username;
    }

    public void changeStatus(UserStatus status){
        this.status = status;
    }

    public void changeRole(UserRoles role){
        this.role = role;
    }
}
