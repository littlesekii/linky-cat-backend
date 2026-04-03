# Linky Cat API

REST API backend for Linky Cat - a personalized link aggregator platform.

## Tech Stack

- **Java 25** + **Spring Boot 4.0.5**
- **PostgreSQL** with JPA/Hibernate
- **Spring Security** + JWT (Auth0 java-jwt)
- **SpringDoc OpenAPI** (Swagger UI)
- **Docker Compose** for PostgreSQL
- **Hexagonal Architecture** (Ports & Adapters)

## Status

🟢 In development

## Project Structure

```
src/main/java/cat/linky/linky_cat_api/
├── adapters/
│   ├── in/web/controller/          # REST Controllers
│   │   ├── dto/                    # Request/Response DTOs
│   │   ├── AuthController.java
│   │   ├── LinkController.java
│   │   └── ProfileController.java
│   └── out/persistence/jpa/        # JPA Adapters
│       ├── link/
│       ├── profile/
│       └── user/
├── core/
│   ├── domain/                     # Domain entities
│   │   ├── Link.java
│   │   ├── Profile.java
│   │   └── User.java
│   ├── exception/                  # Domain exceptions
│   ├── ports/
│   │   ├── in/usecase/             # Input use cases
│   │   ├── in/dto/                 # Internal DTOs (Commands/Results)
│   │   └── out/repository/         # Output ports (Repositories)
│   └── service/                    # Service implementations
├── infra/
│   ├── config/                     # Configuration (Beans, Security, Swagger)
│   └── exception/                  # Global Exception Handler
└── LinkyCatApiApplication.java
```

## Prerequisites

- Java 25
- Docker + Docker Compose
- Maven

## Getting Started

### 1. Start the database

```bash
docker-compose up -d
```

### 2. Run the application

```bash
./mvnw spring-boot:run
```

API available at: `http://localhost:1001`

Swagger UI: `http://localhost:1001/swagger-ui.html`

## Endpoints

### Authentication

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register new user |
| POST | `/api/auth/login` | Login and get JWT token |

### Profiles

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/profiles/{username}` | Get public profile |
| PATCH | `/api/profiles/{id}` | Update profile (auth) |

### Links

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/links` | Create new link |
| PATCH | `/api/links/{id}` | Update link |
| DELETE | `/api/links/{id}` | Delete link |

## Request Examples

### Register user

```bash
curl -X POST http://localhost:1001/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "linkycat",
    "email": "linkycat@linky.cat",
    "password": "secure123",
    "passwordConfirmation": "secure123",
    "displayName": "Linky Cat",
    "bio": "Sandbox developer"
  }'
```

### Login

```bash
curl -X POST http://localhost:1001/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "linkycat",
    "password": "secure123"
  }'
```

### Create link

```bash
curl -X POST http://localhost:1001/api/links \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer {jwt-token}" \
  -d '{
    "title": "GitHub",
    "url": "https://github.com/littlesekii",
    "sortOrder": 1,
    "isActive": true
  }'
```

### Update profile

```bash
curl -X PATCH http://localhost:1001/api/profiles/{id} \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer {jwt-token}" \
  -d '{
    "displayName": "Linky Cat Updated",
    "bio": "New bio here"
  }'
```

### Get profile

```bash
curl http://localhost:1001/api/profiles/linkycat
```

## Environment Variables

| Variable | Default | Description |
|----------|---------|-------------|
| `SERVER_PORT` | 1001 | Application port |
| `SPRING_DATASOURCE_URL` | jdbc:postgresql://localhost:3001/linkycat | Database URL |
| `SPRING_DATASOURCE_USERNAME` | lcuser | Database user |
| `SPRING_DATASOURCE_PASSWORD` | 123 | Database password |
| `JWT_SECRET` | (required) | Secret key for JWT signing (min 32 chars) |
| `JWT_EXPIRATION` | 1800 | Token expiration time in seconds |
| `JWT_ISSUER` | linky_cat_api | Token issuer |
