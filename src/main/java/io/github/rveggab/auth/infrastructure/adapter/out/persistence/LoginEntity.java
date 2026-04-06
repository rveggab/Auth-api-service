package io.github.rveggab.auth.infrastructure.adapter.out.persistence;

import io.github.rveggab.auth.domain.model.enums.AppStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(schema = "iam", name = "login_control")
@Getter
@Setter
public class LoginEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_id", nullable = false)
    private AppEntity app;

    @Column(nullable = false, length = 1)
    @Enumerated(EnumType.STRING)
    private AppStatus status;

    @Column(name = "attempts", nullable = false)
    private Integer attempts;

    @Column(name = "lockout_Until")
    private Instant lockoutUntil;

    @Column(name = "last_Ip")
    private String lastIp;
}
