package io.github.rveggab.auth.infrastructure.adapter.out.persistence;

import io.github.rveggab.auth.domain.model.enums.AppStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(schema = "iam", name = "application", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name"),
        @UniqueConstraint(columnNames = "client_id")
})
@Setter
@Getter
public class AppEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "client_id", nullable = false, unique = true)
    private String clientId;

    @Column(name = "client_secret", nullable = false)
    private String clientSecret;

    @Column(name = "base_url", nullable = false)
    private String baseUrl;

    @Column(nullable = false, length = 1)
    @Enumerated(EnumType.STRING)
    private AppStatus status;

    @Column(updatable = false, insertable = false)
    private Instant createdAt;
}
