package io.github.rveggab.auth.application.ports.out;

import io.github.rveggab.auth.domain.model.identity.App;

import java.util.List;
import java.util.Optional;

public interface AppRepositoryOutPort {
    Optional<App> findById(Long id);
    Optional<App> findByName(String appName);
    Optional<App> findByUrl(String appUrl);
    List<App> findAll();
    App save(App app);
}
