package com.example.health_platform.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiDetails() {
        return new OpenAPI()
                .info(new Info()
                        .title("HealthSphere API")
                        .version("1.0")
                        .description("API documentation for HealthSphere platform")
                        .contact(new Contact()
                                .name("HealthSphere Support")
                                .email("support@healthsphere.com")
                        )
                        .license(new License()
                                .name("Apache License 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")
                        )
                )
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Local Server"),
                        new Server().url("https://healthsphere.com/api").description("Production Server")
                ));
    }
}
