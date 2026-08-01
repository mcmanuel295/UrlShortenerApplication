# UrlShortener

## Overview

UrlShortener is a Spring Boot application that provides a simple URL shortening service. It stores original URLs and generates unique short codes for retrieval and redirection.

## Key Features

- Shorten a long URL into a unique short code
- Retrieve the short code for a previously stored original URL
- Resolve a short code back to the original URL
- Persist URL mappings using Spring Data JPA and MySQL

## Technology Stack

- Java 21
- Spring Boot 4.1
- Spring Web MVC
- Spring Data JPA
- MySQL Connector/J
- Lombok

## API Endpoints

All endpoints are mounted under `/api/url`.

- `POST /api/url/` — Create a short URL for an original URL
- `GET /api/url/short-code?originalUrl={originalUrl}` — Retrieve the short code for a given original URL
- `GET /api/url/original-url?shortCode={shortCode}` — Retrieve the original URL for a given short code

## Configuration

The default data source is configured in `src/main/resources/application.yaml`:



## Build and Run

Use Maven to build and run the application locally.

```bash
./mvnw clean package
./mvnw spring-boot:run
```

The service starts on port `8080` by default.

## Testing

Run unit and integration tests with:

```bash
./mvnw test
```

## Notes

- The application expects a MySQL database instance.
- The service uses JPA entity mapping to persist URL records.
- Ensure MySQL is running and accessible before starting the application.

## License

This project is provided as-is without a specific license.
 
