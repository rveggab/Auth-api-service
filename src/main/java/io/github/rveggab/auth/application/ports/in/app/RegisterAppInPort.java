package io.github.rveggab.auth.application.ports.in.app;

import io.github.rveggab.auth.domain.model.identity.App;

public interface RegisterAppInPort {
    App execute(App app);
}
