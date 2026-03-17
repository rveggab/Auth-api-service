package io.github.rveggab.auth.infrastructure.adapter.in.web.handler;

import io.github.rveggab.auth.domain.exceptions.AuthException;
import io.github.rveggab.auth.infrastructure.adapter.in.web.dto.BaseApiResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AuthException.class)
    public ResponseEntity<BaseApiResponse<Void>> handleDomainException(AuthException dex){
        return buildResponse(dex.getStatusCode(), dex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseApiResponse<Void>> handleUnexpected(Exception ex){
        return buildResponse(500, "Error inesperado");
    }

    private ResponseEntity<BaseApiResponse<Void>> buildResponse(int status, String message) {

        BaseApiResponse<Void> responseBody = BaseApiResponse.<Void>builder()
                .timestamp(LocalDateTime.now())
                .status(status)
                .message(message)
                .build();

        return new ResponseEntity<>(responseBody, HttpStatusCode.valueOf(status));
    }
}
