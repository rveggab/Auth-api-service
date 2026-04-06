package io.github.rveggab.auth.application.ports.in.app;

import io.github.rveggab.auth.infrastructure.adapter.in.web.dto.request.app.UpdateAppRequest;

public interface UpdateAppInPort {
    void updateAppData(Long id, UpdateAppRequest request);
    void delete(Long id);
}
