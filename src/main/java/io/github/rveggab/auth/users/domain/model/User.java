package io.github.rveggab.auth.users.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private UUID id;
    private String name;
    private String paternalSurname;
    private String maternalSurname;
    private UserStatus status;

    public boolean isActive(){
        return status.canAuth();
    }

    public static User create(String name, String paternalSurname, String maternalSurname) {

        User user = new User();

        user.name = name;
        user.paternalSurname = paternalSurname;
        user.maternalSurname = maternalSurname;
        user.status = UserStatus.A;

        return user;
    }
}
