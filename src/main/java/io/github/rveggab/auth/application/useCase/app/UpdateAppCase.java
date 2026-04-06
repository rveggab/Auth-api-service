package io.github.rveggab.auth.application.useCase.app;

import io.github.rveggab.auth.application.ports.in.app.UpdateAppInPort;
import io.github.rveggab.auth.application.ports.out.AppRepositoryOutPort;
import io.github.rveggab.auth.domain.exceptions.EntityNotFoundException;
import io.github.rveggab.auth.domain.model.enums.AppStatus;
import io.github.rveggab.auth.domain.model.identity.App;
import io.github.rveggab.auth.infrastructure.adapter.in.web.dto.request.app.UpdateAppRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateAppCase implements UpdateAppInPort {

    private final AppRepositoryOutPort appRepo;

    @Override
    public void updateAppData(Long id, UpdateAppRequest request) {
        App app = appRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("App not found"));

        if (request.getName() != null && !request.getName().isEmpty())
            app.changeAppName(app.getName());

        if (request.getBaseUrl() != null && !request.getBaseUrl().isEmpty())
            app.changeUrl(request.getBaseUrl());

        appRepo.save(app);
    }

    @Override
    public void delete(Long id) {
        App app = appRepo.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Cannot delete this app"));

        app.changeStatus(AppStatus.I);

        appRepo.save(app);
    }
}
