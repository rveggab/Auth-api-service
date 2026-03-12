package io.github.rveggab.auth.infrastructure.adapter.mapper;

import io.github.rveggab.auth.domain.model.identity.User;
import io.github.rveggab.auth.infrastructure.adapter.out.UserEntity;

public class UserMapper {
    public static User toDomain(UserEntity entity){
        if (entity == null) return null;
        return new User(
                entity.getId(),
                entity.getName(),
                entity.getMiddleName(),
                entity.getLastName(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getRole(),
                entity.getStatus()
        );
    }

    public static UserEntity toEntity(User domain){
        if (domain == null) return null;
        UserEntity entity = new UserEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setMiddleName(domain.getMiddleName());
        entity.setLastName(domain.getLastName());
        entity.setEmail(domain.getEmail());
        entity.setPassword(domain.getPassword());
        entity.setRole(domain.getRole());
        entity.setStatus(domain.getStatus());
        return entity;
    }
}
