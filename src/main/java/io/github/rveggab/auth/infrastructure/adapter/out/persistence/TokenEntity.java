package io.github.rveggab.auth.infrastructure.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(schema = "iam", name = "refresh_token")
@Getter
@Setter
public class TokenEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_Id", nullable = false)
    private SessionEntity session;

    @Column(name = "hashed_Token", nullable = false)
    private String hashToken;

    @Column(name = "created_At", insertable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "expires_At")
    private Instant expiresAt;

    @Column(name = "revoked_At")
    private Instant revokedAt;

    @Column(name = "replaced_Token")
    private UUID replaceByToken;
}
