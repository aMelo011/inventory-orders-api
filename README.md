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
- Maven

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

### Products
| Method | Endpoint | Role | Description |
|--------|----------|------|-------------|
| POST | `/api/products` | ADMIN | Create a product (with optional category) |
| GET | `/api/products?page=0&size=10` | any | List products (paginated) |
| GET | `/api/products/{id}` | any | Get a product by id |
| PUT | `/api/products/{id}` | ADMIN | Update a product |
| DELETE | `/api/products/{id}` | ADMIN | Delete a product |

### Categories
| Method | Endpoint | Role | Description |
|--------|----------|------|-------------|
| POST | `/api/categories` | ADMIN | Create a category |
| GET | `/api/categories?page=0&size=10` | any | List all categories (paginated) |

### Orders
| Method | Endpoint | Role | Description |
|--------|----------|------|-------------|
| POST | `/api/orders` | any | Create an order with items |
| GET | `/api/orders?page=0&size=10` | any | List orders for authenticated user (paginated) |

## Authentication

All endpoints except `/api/auth/**` require a Bearer token in the Authorization header.
New users are registered with the `USER` role by default. `ADMIN` role is required for creating, updating, and deleting products and categories.

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
| V8 | Add role to app_user table |

## Tests

### Unit Tests (8 tests)
Tests using Mockito.

- **ProductServiceTest** — create product, list products (paginated), throw on product not found
- **CategoryServiceTest** — create category, list categories (paginated)
- **OrderServiceTest** — throw on email not found, throw on product not found, create order successfully

### Integration Tests (4 tests)
Tests using Testcontainers — real PostgreSQL, real Spring context, real HTTP requests.

- Create product with ADMIN role (register → login → create product)
- Reject product creation with USER role (403 Forbidden)
- Reject duplicate email on register (409 Conflict)
- Reject login with wrong password (401 Unauthorized)

## Status

Features complete. Built incrementally as a learning project for backend Java development.
