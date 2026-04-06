package io.github.rveggab.auth.infrastructure.adapter.in.app;

import io.github.rveggab.auth.application.ports.out.AppRepositoryOutPort;
import io.github.rveggab.auth.domain.model.identity.App;
import io.github.rveggab.auth.infrastructure.adapter.mapper.app.AppMapper;
import io.github.rveggab.auth.infrastructure.adapter.out.persistence.AppEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AppPersistenceAdapter implements AppRepositoryOutPort {

    private final JpaAppRepository appRepository;

    @Override
    public Optional<App> findById(Long id) {
        return appRepository.findById(id).map(AppMapper::toDomain);
    }

    @Override
    public Optional<App> findByName(String name) {
        return appRepository.findByName(name).map(AppMapper::toDomain);
    }

    @Override
    public Optional<App> findByUrl(String appUrl) {
        return appRepository.findByBaseUrl(appUrl).map(AppMapper::toDomain);
    }

    @Override
    public List<App> findAll() {
        return appRepository.findAll().stream()
                .map(AppMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public App save(App app) {
        AppEntity entity = AppMapper.toEntity(app);
        AppEntity saveEntity = appRepository.save(entity);
        return AppMapper.toDomain(saveEntity);
    }
}
