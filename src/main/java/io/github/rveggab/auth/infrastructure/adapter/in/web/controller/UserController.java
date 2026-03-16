package io.github.rveggab.auth.infrastructure.adapter.in.web.controller;

import io.github.rveggab.auth.application.ports.in.FindUserInPort;
import io.github.rveggab.auth.application.ports.in.RegisterUserInPort;
import io.github.rveggab.auth.application.ports.in.UpdateUserInPort;
import io.github.rveggab.auth.domain.model.identity.User;
import io.github.rveggab.auth.infrastructure.adapter.in.web.dto.BaseApiResponse;
import io.github.rveggab.auth.infrastructure.adapter.in.web.dto.request.UpdateUserAdminRequest;
import io.github.rveggab.auth.infrastructure.adapter.in.web.dto.request.UpdateUserRequest;
import io.github.rveggab.auth.infrastructure.adapter.in.web.dto.request.UserRequest;
import io.github.rveggab.auth.infrastructure.adapter.in.web.dto.response.UserDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final RegisterUserInPort registerUserInPort;
    private final FindUserInPort findUserInPort;
    private final UpdateUserInPort updateUserInPort;

    @GetMapping("/")
    public List<User> findUsers() {
        return findUserInPort.execute();
    }

    @GetMapping("/{identifier}")
    public ResponseEntity<BaseApiResponse<UserDetailResponse>> find(@PathVariable String identifier) {
        User user;

        if (identifier.matches("\\d+")) {
            user = findUserInPort.executeWithId(Long.parseLong(identifier));
        } else if (identifier.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            user = findUserInPort.executeWithEmail(identifier);
        } else {
            user = findUserInPort.executeWithUsername(identifier);
        }

        UserDetailResponse details = UserDetailResponse.builder()
                .name(user.getName())
                .middleName(user.getMiddleName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .status(user.getStatus().name())
                .build();

        return ResponseEntity.ok(
                BaseApiResponse.<UserDetailResponse>builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.OK.value())
                        .message("successful search")
                        .data(details)
                        .build()
        );
    }

    @PostMapping("/register")
    public ResponseEntity<BaseApiResponse<Void>> register(@RequestBody UserRequest request) {

        User newUser = new User(
                null,
                request.getName(),
                request.getMiddleName(),
                request.getLastName(),
                request.getUsername(),
                request.getEmail(),
                request.getPassword(),
                null,
                null
        );

        registerUserInPort.execute(newUser, request.getPasswordConfirmation());

        return ResponseEntity.status(HttpStatus.CREATED).body(

                BaseApiResponse.<Void>builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.CREATED.value())
                        .message("Has created new user successful")
                        .build()
        );
    }

    @PatchMapping("/{id}/profile")
    public ResponseEntity<BaseApiResponse<Void>> updateProfile(
            @PathVariable Long id,
            @RequestBody UpdateUserRequest request
    ) {
        updateUserInPort.updateProfile(id, request);

        return ResponseEntity.ok(
                BaseApiResponse.<Void>builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.OK.value())
                        .message("Profile updated successful")
                        .build()
        );
    }

    @PatchMapping("/{id}/permission")
    public ResponseEntity<BaseApiResponse<Void>> removeUserField(
            @PathVariable Long id,
            @RequestBody UpdateUserAdminRequest adminRequest
    ) {

        updateUserInPort.updatePermission(id, adminRequest);

        return ResponseEntity.ok(
                BaseApiResponse.<Void>builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.OK.value())
                        .message("Permission updated successful")
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseApiResponse<Void>> deleteUser(
            @PathVariable Long id
    ){
        updateUserInPort.delete(id);

        return ResponseEntity.ok(
                BaseApiResponse.<Void>builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.OK.value())
                        .message("Usuario dado de baja exitosamente")
                        .build()
        );
    }
}
