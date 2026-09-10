package io.github.rveggab.auth.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Auth Api Service",
                version = "1.1.0",
                contact = @Contact(
                        name = "Ricardo Vega Gabino",
                        email = "rvgabino36@gmail.com"
                ),
                license = @License(
                        name = "MIT"
                )
        ),
        servers = @Server(
                url = "http://localhost:8081",
                description = "Development"
        )
)
@Configuration
public class SwaggerConfig {
}
