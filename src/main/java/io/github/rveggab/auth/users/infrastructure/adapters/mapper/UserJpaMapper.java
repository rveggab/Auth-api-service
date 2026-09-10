package io.github.rveggab.auth.users.infrastructure.adapters.mapper;

import io.github.rveggab.auth.users.domain.model.User;
import io.github.rveggab.auth.users.infrastructure.adapters.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserJpaMapper {

    UserEntity toUserEntity(User User);

    User toUser(UserEntity entity);

    List<User> toUserList(List<UserEntity> entityList);
}
