package io.github.rveggab.auth.infrastructure.adapter.in.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseApiResponse<T> {
    private LocalDateTime timestamp;
    private int status;
    private String message;
    private T data;
}
