package io.github.rveggab.auth.domain.model.identity;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    private UUID id;
    private User user;
    private App app;
    private String ipAddress;
    private String userAgent;
    private String deviceHash;
    private LocalDateTime expiresAt;
    private LocalDateTime revokedAt;
}
