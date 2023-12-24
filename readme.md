# Flight Search API ✈️

This project provides a RESTful API for managing and searching flight information.

##  📌 Technologies and Tools

- Java - Spring Boot
- Postgresql
- Quartz Scheduler
- Spring Security
- JWT
- Git
- Swagger

## 📌 Database Model

The data model for the database includes the following entities:

- Flights
    - ID
    - Departure Airport
    - Arrival Airport
    - Departure Date/Time
    - Return Date/Time
    - Price

- Airports
    - ID
    - City

## 📌 CRUD Operations

The API supports CRUD (Create, Read, Update, Delete) operations for the following resources:

- Flights
- Airports

These operations ensure consistent and organized management of data, allowing users to create, read, update, and delete data as needed.

## 📌 Search Operations

The API includes an endpoint that lists flights based on the given departure location, arrival location, departure date, and return date. If no return date is provided, it represents a one-way flight; otherwise, it represents a round-trip flight. The API returns either a single flight information for one-way or two flight information for round-trip.

## 📌 Authentication

This API uses a JWT (JSON Web Token) based authentication structure. A valid token is required to access any endpoint.

### 📌 Spring Security

Authentication and authorization processes are handled by Spring Security. Token creation, validation, and authorization processes are implemented using Spring Security.

## 📌 Scheduled Background Jobs

The project includes a scheduled job that makes a daily request to a mock API, retrieves flight information, and stores it in the database.

#### Quartz Scheduler

Quartz Scheduler is used to manage background tasks. It is employed to schedule and execute tasks at specific intervals.

####  Flight Data Generator Service

The `FlightDataGeneratorService` class is responsible for generating random flight information, which is used to mock flight data for testing and development purposes.

## 📌 Exception Handling

The project incorporates a robust exception handling mechanism to ensure smooth error handling and provide informative responses to clients.

### 📌 Generic Exception Handling

A centralized exception handling approach is implemented to capture and process exceptions thrown during API requests. This ensures consistent and standardized error responses across the application.
#### NotFoundException

The `NotFoundException` is thrown when a requested resource is not found. The exception handling mechanism returns an appropriate HTTP status code (404 Not Found) along with a custom error message in the response body.
![swagger](/src/main/resources/static/customnotfound.jpg)

#### CustomJwtException

The `CustomJwtException` is thrown when there is an issue with JWT (JSON Web Token) authentication. The exception handling mechanism returns an appropriate HTTP status code (e.g., 401 Unauthorized) along with a custom error message in the response body.

![swagger](/src/main/resources/static/emailalreadyuse.jpg)


## 📌 API Documentation

![swagger](/src/main/resources/static/swagger.jpg)

The API documentation is available via Swagger UI. Visit [Swagger UI](http://localhost:8080/swagger-ui/index.html) to explore and interact with the API endpoints.index.html).
