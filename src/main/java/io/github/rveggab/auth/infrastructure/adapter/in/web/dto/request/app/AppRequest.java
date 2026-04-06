package io.github.rveggab.auth.infrastructure.adapter.in.web.dto.request.app;

import io.github.rveggab.auth.infrastructure.utils.validations.urls.ValidUrl;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppRequest {
    @Parameter(name = "name", description = "The app name", required = true)
    @Size(max = 60)
    @NotBlank
    private String name;
    @Parameter(name = "url", description = "The app link that deploy", required = true)
    @ValidUrl(message = "This address does not match with standard")
    @NotBlank
    private String url;
}
