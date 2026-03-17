package io.github.rveggab.auth.infrastructure.adapter.out;

import io.github.rveggab.auth.domain.model.enums.UserRoles;
import io.github.rveggab.auth.domain.model.enums.UserStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(schema = "iam", name = "users")
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, updatable = false)
    private String name;
    @Column(nullable = false, updatable = false)
    private String middleName;
    @Column(nullable = false, updatable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, length = 1)
    @Enumerated(EnumType.STRING)
    private UserRoles role;
    @Column(nullable = false, length = 1)
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
}
