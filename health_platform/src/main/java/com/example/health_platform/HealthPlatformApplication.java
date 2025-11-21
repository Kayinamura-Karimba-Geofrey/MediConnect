package com.example.health_platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling

@EnableJpaAuditing
public class HealthPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthPlatformApplication.class, args);
	}

}
