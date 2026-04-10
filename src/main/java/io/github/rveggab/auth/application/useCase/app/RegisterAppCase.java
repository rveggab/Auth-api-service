package io.github.rveggab.auth.application.useCase.app;

import io.github.rveggab.auth.application.ports.in.app.RegisterAppInPort;
import io.github.rveggab.auth.application.ports.out.AppRepositoryOutPort;
import io.github.rveggab.auth.domain.model.identity.App;
import io.github.rveggab.auth.infrastructure.utils.helpers.ClaveGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterAppCase implements RegisterAppInPort {
    private final AppRepositoryOutPort outPort;

    @Override
    public App execute(App app) {

        // create new clientID
        String cleanName = app.getName().replace(" ", "_");
        String newClientId = ClaveGenerator.buildClave(cleanName, 3);
        app.createId(newClientId);

        // create new client secret
        String newSecret = ClaveGenerator.generatePassword(12);
        app.createSecret(newSecret);

        app.assignDefaultStatus();

        return outPort.save(app);
    }
}
