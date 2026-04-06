package io.github.rveggab.auth.application.useCase.app;

import io.github.rveggab.auth.application.ports.in.app.FindAppInPort;
import io.github.rveggab.auth.application.ports.out.AppRepositoryOutPort;
import io.github.rveggab.auth.domain.exceptions.EntityNotFoundException;
import io.github.rveggab.auth.domain.model.identity.App;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class FindAppCase implements FindAppInPort {
    private final AppRepositoryOutPort appRepo;

    @Override
    public App executeWithName(String appName) {
        return appRepo.findByName(appName)
                .orElseThrow(() -> new EntityNotFoundException("This app name not found"));
    }

    @Override
    public App executeWithUrl(String url) {
        return appRepo.findByUrl(url)
                .orElseThrow(() -> new EntityNotFoundException("This url not found"));
    }

    @Override
    public List<App> execute() {
        return appRepo.findAll();
    }
}
