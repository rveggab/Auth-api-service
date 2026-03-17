package io.github.rveggab.auth.infrastructure.adapter.mapper;

import io.github.rveggab.auth.domain.model.identity.User;
import io.github.rveggab.auth.infrastructure.adapter.in.web.dto.response.UserDetailResponse;

import java.util.List;

public class UserResponseMapper {

    public static UserDetailResponse toUserDetail(User user){
        return UserDetailResponse.builder()
                .name(user.getName())
                .middleName(user.getMiddleName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .status(user.getStatus().name())
                .build();
    }

    public static List<UserDetailResponse> toDetailResponseList(List<User> users) {
        return users.stream()
                .map(UserResponseMapper::toUserDetail)
                .toList();
    }
}
