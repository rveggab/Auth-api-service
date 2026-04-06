package io.github.rveggab.auth.infrastructure.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(schema = "iam", name = "sessions")
@Getter
@Setter
public class SessionEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_id", nullable = false)
    private AppEntity app;

    @Column(name = "ip_Address", nullable = false, length = 45)
    private String ipAddress;

    @Column(name = "user_Agent", nullable = false)
    private String userAgent;

    @Column(name = "device_Hash", nullable = false)
    private String deviceHash;

    @Column(name = "created_At", insertable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "expires_At")
    private Instant expiresAt;

    @Column(name = "revoked_At")
    private Instant revokedAt;
}
