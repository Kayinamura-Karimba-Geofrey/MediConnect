package com.example.health_platform.config;



import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
                );
    }
}

