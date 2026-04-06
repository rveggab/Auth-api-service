package io.github.rveggab.auth.infrastructure.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "user_role",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"user_app_id", "role_id"}
        ))
@Setter
@Getter
public class UserRoleEntity {
        @Id
        @GeneratedValue
        private UUID id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_app_id", nullable = false)
        private UserAppEntity userApp;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "role_id", nullable = false)
        private RoleEntity role;
}
