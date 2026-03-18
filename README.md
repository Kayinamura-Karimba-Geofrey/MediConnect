# MediConnect Health Platform

MediConnect is a modern, Spring Boot-based healthcare management system designed to streamline interactions between patients, doctors, and administrators.

## Features
- **Patient Management**: Registration, profile, and medical history.
- **Appointment Scheduling**: Real-time booking and management.
- **Doctor Portal**: Manage consultations, prescriptions, and patients.
- **Admin Dashboard**: Analytics, user management, and system configuration.
- **Billing & Insurance**: Integrated billing and insurance tracking.

## Tech Stack
- **Backend**: Spring Boot 3.2.4, Java 21
- **Database**: PostgreSQL (Neon DB)
- **Security**: Spring Security, JWT
- **Documentation**: Swagger/OpenAPI
- **Containerization**: Docker & Docker Compose

## Getting Started
1. Clone the repository.
2. Set up your environment variables (`DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, `JWT_SECRET`).
3. Run with Maven: `./mvnw spring-boot:run`
4. Access API documentation at `/swagger-ui.html`.
