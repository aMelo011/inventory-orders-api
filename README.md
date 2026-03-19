# Inventory & Orders API

A REST API for managing products, categories, and orders — built as a learning project to practice backend development with Java and Spring Boot.

## Stack

- Java 21
- Spring Boot 3.5
- Spring Data JPA (Hibernate 6)
- Spring Security + JWT
- PostgreSQL 16
- Flyway (database migrations)
- JUnit 5 + Mockito
- Docker + docker-compose
- GitHub Actions (CI)

## Getting Started

### Prerequisites

- Java 21
- Docker + docker-compose
- Maven (included via `mvnw`)

### Run the database

```bash
cd apps/inventory-api
docker compose up -d
```

### Run the application

```bash
cd apps/inventory-api
./mvnw spring-boot:run
```

The API starts on `http://localhost:8080`.

### Run the tests

```bash
cd apps/inventory-api
./mvnw test
```

## API Endpoints

### Authentication (public)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Create a new account |
| POST | `/api/auth/login` | Login and receive a JWT token |

### Products (requires JWT)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/products` | Create a product (with optional category) |
| GET | `/api/products?page=0&size=10` | List products (paginated) |
| GET | `/api/products/{id}` | Get a product by id |
| PUT | `/api/products/{id}` | Update a product |
| DELETE | `/api/products/{id}` | Delete a product |

### Categories (requires JWT)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/categories` | Create a category |
| GET | `/api/categories` | List all categories |

### Orders (requires JWT)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/orders` | Create an order with items |

## Authentication

All endpoints except `/api/auth/**` require a Bearer token in the Authorization header.

1. Register: `POST /api/auth/register` with `{"email": "user@mail.com", "password": "123456"}`
2. Login: `POST /api/auth/login` with the same credentials — returns a JWT token
3. Use the token: add `Authorization: Bearer <token>` to all other requests

## Project Structure

```
com.melo.inventory/
  controller/       — REST endpoints
  model/             — Entities and DTOs
  service/           — Business logic
  repository/        — Database access (Spring Data JPA)
  exception/         — Global error handling
  security/          — JWT authentication and Spring Security config
```

## Database Migrations

Managed by Flyway. Migration files are in `src/main/resources/db/migration/`.

| Version | Description |
|---------|-------------|
| V1 | Create product table |
| V2 | Change price from double precision to numeric |
| V3 | Create category table |
| V4 | Add category foreign key to product |
| V5 | Create app_user table |
| V6 | Create orders table |
| V7 | Create order_item table |

## Status

In progress — built incrementally as a learning project.
