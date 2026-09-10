package io.github.rveggab.auth.users.infrastructure.adapters.entity;

import io.github.rveggab.auth.users.domain.model.UserStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "iam", name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String paternalSurname;
    @Column(nullable = false)
    private String maternalSurname;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    @Column(nullable = false, updatable = false, insertable = false)
    private Instant createdAt;
    @Column(nullable = false, updatable = false, insertable = false)
    private Instant deletedAt;
}
