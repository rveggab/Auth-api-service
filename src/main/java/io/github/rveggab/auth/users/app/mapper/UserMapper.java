package io.github.rveggab.auth.users.app.mapper;

import io.github.rveggab.auth.users.domain.model.User;
import io.github.rveggab.auth.users.infrastructure.rest.request.UserRequest;
import io.github.rveggab.auth.users.infrastructure.rest.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User toUser(UserRequest request);

    UserResponse toUserResponse(User user);

    List<UserResponse> toUserResponseList(List<User> userList);

}
