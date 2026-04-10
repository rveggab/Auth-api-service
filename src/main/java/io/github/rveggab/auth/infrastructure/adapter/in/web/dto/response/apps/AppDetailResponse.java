package io.github.rveggab.auth.infrastructure.adapter.in.web.dto.response.apps;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppDetailResponse {
    private Long id;
    private String appName;
    private String idClient;
    private String secret;
    private String url;
    private String status;
}
