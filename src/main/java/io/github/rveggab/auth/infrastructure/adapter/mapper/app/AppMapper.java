package io.github.rveggab.auth.infrastructure.adapter.mapper.app;

import io.github.rveggab.auth.domain.model.identity.App;
import io.github.rveggab.auth.infrastructure.adapter.out.persistence.AppEntity;

public class AppMapper {
    public static App toDomain(AppEntity entity) {
        if (entity == null) return null;

        return new App(
                entity.getId(),
                entity.getName(),
                entity.getClientId(),
                entity.getClientSecret(),
                entity.getBaseUrl(),
                entity.getStatus()
        );
    }

    public static AppEntity toEntity(App domain){
        if (domain == null) return null;

        AppEntity entity = new AppEntity();

        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setClientId(domain.getClientId());
        entity.setClientSecret(domain.getClientSecret());
        entity.setBaseUrl(domain.getUrl());
        entity.setStatus(domain.getStatus());

        return entity;
    }
}
