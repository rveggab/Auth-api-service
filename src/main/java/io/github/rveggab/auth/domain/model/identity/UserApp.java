package io.github.rveggab.auth.domain.model.identity;

import io.github.rveggab.auth.domain.model.enums.AppStatus;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserApp {
    private Long id;
    private User user;
    private App app;
    private AppStatus status;
}
