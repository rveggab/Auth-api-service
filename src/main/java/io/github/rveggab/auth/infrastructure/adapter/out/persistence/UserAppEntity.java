package io.github.rveggab.auth.infrastructure.adapter.out.persistence;

import io.github.rveggab.auth.domain.model.enums.AppStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(schema = "iam", name = "User_Access")
@Setter
@Getter
public class UserAppEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_id", nullable = false)
    private AppEntity app;

    @Column(nullable = false, length = 1)
    @Enumerated(EnumType.STRING)
    private AppStatus status;

    @Column(nullable = false, insertable = false, updatable = false)
    private Instant createdAt;
}
