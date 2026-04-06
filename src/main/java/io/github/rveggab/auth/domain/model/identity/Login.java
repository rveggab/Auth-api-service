package io.github.rveggab.auth.domain.model.identity;

import io.github.rveggab.auth.domain.model.enums.AppStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Login {
    private UUID id;
    private User user;
    private App app;
    private AppStatus status;
    private Integer attempts;
    private String lastIp;
    private LocalDateTime lockoutUntil;
}
