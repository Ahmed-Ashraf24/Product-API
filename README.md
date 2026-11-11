# Product Management API

A RESTful backend API for managing product data, built with **Kotlin and Spring Boot**. The application provides product management operations through REST endpoints, using PostgreSQL for persistent data storage and Spring Data JPA for database interaction.

## Features

- **Product Management:** Create, retrieve, update, and delete products.
- **Database Integration:** Persistent storage using PostgreSQL.
- **Spring Data JPA:** Repository-based database operations.
- **Layered Architecture:** Separation of controller, service, repository, and entity responsibilities.
- **Authentication:** API access protected using Spring Security.
- **Environment-based Configuration:** Database and authentication credentials configured through environment variables.
- **Exception Handling:** Handles duplicate products and products that do not exist.

## Technologies

| Technology | Purpose |
|---|---|
| Kotlin | Backend programming language |
| Spring Boot | Application framework |
| Spring Web | REST API development |
| Spring Data JPA | Database access and persistence |
| Hibernate | ORM |
| PostgreSQL | Relational database |
| Spring Security | API authentication |
| Gradle | Build automation |
| Java 17 | Runtime environment |

## Project Structure

The application is organized into the following main Kotlin classes:

| Class | Responsibility |
|---|---|
| `DemoApiApplication.kt` | Spring Boot application entry point |
| `ItemsController.kt` | Handles product-related HTTP requests |
| `ProductsService.kt` | Contains product business logic |
| `ProductRepository.kt` | Handles database operations |
| `ProductEntity.kt` | Defines the product database entity |
| `ProductExceptions.kt` | Defines custom product exceptions |
| `ProductExceptionHandler.kt` | Handles application exceptions |
| `SecurityConfig.kt` | Configures API security |

## Requirements

- JDK 17 or later
- PostgreSQL installed and running
- Git
- IntelliJ IDEA or another Kotlin-compatible IDE

## Database Setup

1. Open PostgreSQL or pgAdmin.
2. Create a database named `Product`.
3. Configure the database connection using the environment variables below.

The application uses PostgreSQL to store product information.

## Environment Variables

The application expects the following environment variables:

| Variable | Description |
|---|---|
| `DB_NAME` | PostgreSQL database name |
| `DB_USERNAME` | PostgreSQL username |
| `DB_PASSWORD` | PostgreSQL password |
| `API_PASSWORD` | API authentication password |

Example configuration (Windows Command Prompt):

```cmd
set DB_NAME=Product
set DB_USERNAME=postgres
set DB_PASSWORD=your_database_password
set API_PASSWORD=your_api_password
```

**Important:** Replace the example values with your own credentials. Never commit actual passwords or sensitive configuration to GitHub.

## Running the Application

### 1. Clone the repository

```bash
git clone https://github.com/Ahmed-Ashraf24/Product-API.git
cd Product-API
```

### 2. Configure environment variables

Set the required environment variables described above.

### 3. Run using Gradle

On Windows:

```cmd
gradlew.bat bootRun
```

Alternatively, open the project in IntelliJ IDEA, configure the environment variables in the run configuration, and run the Spring Boot application.

### 4. Access the API

Once the application starts, the API will be available at:

```text
http://localhost:8080
```

API requests are protected by Spring Security. Use the configured authentication credentials when making requests.

## API Operations

The API provides product management operations:

| HTTP Method | Operation |
|---|---|
| GET | Retrieve products |
| POST | Create a product |
| PUT | Update an existing product |
| DELETE | Delete a product |

Refer to `ItemsController.kt` for the exact endpoint paths, request formats, and parameters.

## Related Project

This API was developed as the backend for a React-based Product Management Dashboard.

- **Frontend Repository:** [Product Management Dashboard](https://github.com/Ahmed-Ashraf24/product-dashboard)
