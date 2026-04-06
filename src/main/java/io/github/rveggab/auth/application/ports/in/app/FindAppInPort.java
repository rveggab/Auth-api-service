package io.github.rveggab.auth.application.ports.in.app;

import io.github.rveggab.auth.domain.model.identity.App;

import java.util.List;

public interface FindAppInPort {
    App executeWithName(String appName);
    App executeWithUrl(String url);
    List<App> execute();
}