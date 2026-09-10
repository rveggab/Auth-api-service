package io.github.rveggab.auth.users.infrastructure.rest.controllers;

import io.github.rveggab.auth.users.app.mapper.UserMapper;
import io.github.rveggab.auth.users.app.ports.input.FindUserServicePort;
import io.github.rveggab.auth.users.app.ports.input.WriteUserServicePort;
import io.github.rveggab.auth.users.infrastructure.rest.request.UserRequest;
import io.github.rveggab.auth.users.infrastructure.rest.response.UserResponse;
import io.github.rveggab.auth.utils.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/users")
@Tag(name = "users", description = "Manager to user endpoints")
@RequiredArgsConstructor
public class UserController {
    private final FindUserServicePort findUserPort;
    private final WriteUserServicePort writeUser;
    private final UserMapper mapper;

    @GetMapping
    @Operation(summary = "Get list with all user", description = "Return a list with all registered users")
    public ResponseEntity<ApiResponse<List<UserResponse>>> findAll() {
        List<UserResponse> users = mapper.toUserResponseList(findUserPort.execute());

        return ResponseEntity.ok(
                ApiResponse.success(
                        HttpStatus.OK.value(),
                        "List of user successfully retrieved",
                        users
                )
        );
    }

    @GetMapping("/name")
    public ResponseEntity<ApiResponse<UserResponse>> findName(@RequestParam String name) {
        UserResponse user = mapper.toUserResponse(findUserPort.executeByName(name));

        return ResponseEntity.ok(
                ApiResponse.success(
                        HttpStatus.OK.value(),
                        "Successful user search",
                        user
                )
        );
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<UserResponse>> create(@RequestBody UserRequest request) {
        UserResponse user = mapper.toUserResponse(writeUser.execute(request));

        return ResponseEntity.ok(
                ApiResponse.success(
                        HttpStatus.OK.value(),
                        "User was successfully created",
                        user
                )
        );
    }
}
