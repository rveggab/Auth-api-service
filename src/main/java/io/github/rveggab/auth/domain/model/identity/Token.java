package io.github.rveggab.auth.domain.model.identity;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    private UUID id;
    private Session session;
    private String hashToken;
    private LocalDateTime expiresAt;
    private LocalDateTime revokedAt;
    private UUID replaceByToken;
}
