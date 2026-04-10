package io.github.rveggab.auth.infrastructure.adapter.in.web.controller;

import io.github.rveggab.auth.application.useCase.app.FindAppCase;
import io.github.rveggab.auth.application.useCase.app.RegisterAppCase;
import io.github.rveggab.auth.application.useCase.app.UpdateAppCase;
import io.github.rveggab.auth.domain.model.identity.App;
import io.github.rveggab.auth.infrastructure.adapter.in.web.dto.BaseApiResponse;
import io.github.rveggab.auth.infrastructure.adapter.in.web.dto.request.app.AppRequest;
import io.github.rveggab.auth.infrastructure.adapter.in.web.dto.request.app.UpdateAppRequest;
import io.github.rveggab.auth.infrastructure.adapter.in.web.dto.response.apps.AppDetailResponse;
import io.github.rveggab.auth.infrastructure.adapter.mapper.app.AppMapperResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/app")
@RequiredArgsConstructor
@Tag(name = "Application", description = "Define app id from access to there")
public class ApplicationController {

    private final RegisterAppCase registerApp;
    private final FindAppCase findApp;
    private final UpdateAppCase updateApp;

    @GetMapping
    @Operation(summary = "Get list with all apps", description = "Get list of all apps from register")
    public ResponseEntity<BaseApiResponse<List<AppDetailResponse>>> findAll() {
        List<App> application = findApp.execute();

        List<AppDetailResponse> response = AppMapperResponse.toDetailList(application);

        return ResponseEntity.ok(
                BaseApiResponse.<List<AppDetailResponse>>builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.OK.value())
                        .message("Successful search")
                        .data(response)
                        .build()
        );
    }

    @GetMapping("/{param}")
    @Operation(summary = "Get app by a param", description = "Search app by any param like name or url")
    public ResponseEntity<BaseApiResponse<AppDetailResponse>> find(@PathVariable String param) {
        App app;

        boolean isUrl = param.matches("^(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}(:\\d+)?$")
                || param.matches("^\\d{1,3}(\\.\\d{1,3}){3}(:\\d+)?$");

        if (isUrl) {
            app = findApp.executeWithUrl(param);
        } else {
            app = findApp.executeWithName(param);
        }

        AppDetailResponse detail = AppMapperResponse.toAppDetail(app);

        return ResponseEntity.ok(
                BaseApiResponse.<AppDetailResponse>builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.OK.value())
                        .message("Successful search")
                        .data(detail)
                        .build()
        );
    }

    @PostMapping
    @Operation(summary = "Create a new app", description = "Register a new app, using a request with all data")
    public ResponseEntity<BaseApiResponse<AppDetailResponse>> register(@RequestBody AppRequest request) {

        App newApp = new App(
                null,
                request.getName(),
                null,
                null,
                request.getUrl(),
                null
        );

        registerApp.execute(newApp);
        AppDetailResponse detail = AppMapperResponse.lastNewLog(newApp);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                BaseApiResponse.<AppDetailResponse>builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.CREATED.value())
                        .data(detail)
                        .message("Has created a new user successful")
                        .build()
        );
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update fields of app", description = "Update fields to app register")
    public ResponseEntity<BaseApiResponse<Void>> updateAppField(
            @PathVariable Long id,
            @RequestBody UpdateAppRequest request
    ) {
        updateApp.updateAppData(id, request);

        return ResponseEntity.ok(
                BaseApiResponse.<Void>builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.OK.value())
                        .message("Has updated to field")
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a app register", description = "Delete the app register, for all users")
    public ResponseEntity<BaseApiResponse<Void>> deleteUserField(
            @PathVariable Long id
    ) {
        updateApp.delete(id);

        return ResponseEntity.ok(
                BaseApiResponse.<Void>builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.OK.value())
                        .message("This app has deleted successful")
                        .build()
        );
    }
}
