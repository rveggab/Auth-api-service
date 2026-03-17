package io.github.rveggab.auth.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info =  @Info(
                title = "Auth api service",
                version = "1.0.0",
                contact = @Contact(
                        name = "Ricardo Vega",
                        email = "rvgabino36@gmail.com"
                ),
                license = @License(
                        name = "Apache 2.0"
                )
        ),
        servers = @Server(
                url = "http://localhost:8081",
                description = "Development"
        )
)

@Configuration
public class OpenApiConfig {
}
