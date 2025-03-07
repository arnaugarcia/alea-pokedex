# Pokedex Application

This is a Java Spring Boot application that provides an API to fetch and display Pokémon data.

## Decisions and Improvements
### Decisions Made
* API Design: The API provides endpoints to fetch and sort Pokémon data. The design includes query parameters for 
  sorting and limiting the results instead of having separate endpoints for each use case.
* Hexagonal Architecture: The application is structured using the Hexagonal Architecture pattern to separate the 
  business logic from the infrastructure and external dependencies.
### Improvements to Implement
* Error Handling: Improve error handling in the API to provide more detailed and user-friendly error messages. There 
  is a TODO in [PokemonControllerAdvice.java](src/main/java/com/alea/pokedex/infrastructure/controller/PokemonControllerAdvice.java)
* Caching: Implement more efficient caching mechanisms to reduce the load on the external Pokemon API in background 
  instead of fetching the data every time. There is a cache implementation in [PokemonRepository.java](src/main/java/com/alea/pokedex/domain/PokemonRepository.java)
* Security: Add security features such as authentication and authorization to protect the API endpoints if needed.

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
