# Pokedex Application

This is a Java Spring Boot application that provides an API to fetch and display Pokemon data.

## Prerequisites

- Docker
- Java 17 or higher
- Gradle

## Building the Docker Image

To build the Docker image for the application, use the following command:

```sh
./gradlew bootBuildImage
```

This command will create a Docker image for your Spring Boot application.

## Running the Docker Container

To run the Docker container, use the following command:

```sh
docker run -p 8080:8080 <your-image-name>
```

Replace `<your-image-name>` with the name of the Docker image created in the previous step.

## API Endpoints

### Get All Pokemons

```
GET /api/pokemons
```

Fetches a list of all Pokemons.

#### Query Parameters

- `limit` (optional): The maximum number of Pokemons to return.
- `sortBy` (optional): The field to sort the Pokemons by (e.g., `weight`, `height`).

### Example Request

```sh
curl -X GET "http://localhost:8080/api/pokemons?limit=10&sortBy=weight"
```

## Development

### Running Locally

To run the application locally, use the following command:

```sh
./gradlew bootRun
```

The application will be available at `http://localhost:8080`.

### Running Tests

To run the tests, use the following command:

```sh
./gradlew test
```
