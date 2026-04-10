package io.github.rveggab.auth.infrastructure.adapter.in.web.controller;

import io.github.rveggab.auth.application.ports.in.user.FindUserInPort;
import io.github.rveggab.auth.application.ports.in.user.RegisterUserInPort;
import io.github.rveggab.auth.application.ports.in.user.UpdateUserInPort;
import io.github.rveggab.auth.domain.model.identity.User;
import io.github.rveggab.auth.infrastructure.adapter.in.web.dto.BaseApiResponse;
import io.github.rveggab.auth.infrastructure.adapter.in.web.dto.request.user.UpdateUserAdminRequest;
import io.github.rveggab.auth.infrastructure.adapter.in.web.dto.request.user.UpdateUserRequest;
import io.github.rveggab.auth.infrastructure.adapter.in.web.dto.request.user.UserRequest;
import io.github.rveggab.auth.infrastructure.adapter.in.web.dto.response.user.UserDetailResponse;
import io.github.rveggab.auth.infrastructure.adapter.mapper.user.UserResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Tag(name = "User", description = "User management endpoints")
public class UserController {
    private final RegisterUserInPort registerUserInPort;
    private final FindUserInPort findUserInPort;
    private final UpdateUserInPort updateUserInPort;

    @GetMapping
    @Operation(summary = "Get list with all user", description = "Return a list with all registered users")
    public ResponseEntity<BaseApiResponse<List<UserDetailResponse>>> findUsers() {

        List<User> users = findUserInPort.execute();

        List<UserDetailResponse> response = UserResponseMapper.toDetailResponseList(users);

        return ResponseEntity.ok(
                BaseApiResponse.<List<UserDetailResponse>>builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.OK.value())
                        .message("successful search")
                        .data(response)
                        .build()
        );
    }

    @GetMapping("/{identifier}")
    @Operation(summary = "Get user by id", description = "Return a user using unique identifier as id, username or email")
    public ResponseEntity<BaseApiResponse<UserDetailResponse>> find(@PathVariable String identifier) {
        User user;

        if (identifier.matches("\\d+")) {
            user = findUserInPort.executeWithId(Long.parseLong(identifier));
        } else if (identifier.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            user = findUserInPort.executeWithEmail(identifier);
        } else {
            user = findUserInPort.executeWithUsername(identifier);
        }

        UserDetailResponse details = UserResponseMapper.toUserDetail(user);

        return ResponseEntity.ok(
                BaseApiResponse.<UserDetailResponse>builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.OK.value())
                        .message("successful search")
                        .data(details)
                        .build()
        );
    }

    @PostMapping()
    @Operation(summary = "Create a new user", description = "Register a new user using a request with all data")
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
    @Operation(summary = "Update basic data user", description = "Change some field from the last register, this operation is valid for any user registered")
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
    @Operation(summary = "Update permission for users", description = "Change permission of any user if do you have admin profile")
    public ResponseEntity<BaseApiResponse<Void>> removeUserField(
            @RequestHeader("adminId") long admin,
            @PathVariable Long id,
            @RequestBody UpdateUserAdminRequest adminRequest
    ) {

        updateUserInPort.updatePermission(admin,id, adminRequest);

        return ResponseEntity.ok(
                BaseApiResponse.<Void>builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.OK.value())
                        .message("Permission updated successful")
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete some user profile", description = "If do you have admin profile, can you delete a user registered")
    public ResponseEntity<BaseApiResponse<Void>> deleteUser(
            @PathVariable Long id
    ){
        updateUserInPort.delete(id);

        return ResponseEntity.ok(
                BaseApiResponse.<Void>builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.OK.value())
                        .message("This user has be deleted successful")
                        .build()
        );
    }
}
