package io.github.rveggab.auth.infrastructure.adapter.in.app;

import io.github.rveggab.auth.infrastructure.adapter.out.persistence.AppEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaAppRepository extends JpaRepository<AppEntity, Long> {
    Optional<AppEntity> findByName(String appName);
    Optional<AppEntity> findByClientId(String idClient);
    Optional<AppEntity> findByBaseUrl(String url);

}
