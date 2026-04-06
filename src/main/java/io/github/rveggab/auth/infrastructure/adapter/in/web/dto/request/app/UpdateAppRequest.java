package io.github.rveggab.auth.infrastructure.adapter.in.web.dto.request.app;

import io.github.rveggab.auth.domain.model.enums.AppStatus;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdateAppRequest {
    private String name;
    private String baseUrl;
    private AppStatus status;
}
