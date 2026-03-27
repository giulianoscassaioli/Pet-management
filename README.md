# Pet Manager

A REST API built with Spring Boot for managing pet records. Supports creating, retrieving, updating, and deleting pets.

## Tech Stack

- Java 17+
- Spring Boot 3.2
- Spring Data JPA with H2 in-memory database
- Maven

## Running the Application

```bash
mvn spring-boot:run
```

The application starts on `http://localhost:8080`.
H2 console available at `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:petdb`).

## Running Tests

```bash
mvn test
```

## API Endpoints

Base path: `/api/pets`

| Method | Path      | Description      | Status |
|--------|-----------|------------------|--------|
| POST   | `/`       | Create a pet     | 201    |
| GET    | `/`       | Get all pets     | 200    |
| GET    | `/{id}`   | Get pet by ID    | 200    |
| PUT    | `/{id}`   | Update a pet     | 200    |
| DELETE | `/{id}`   | Delete a pet     | 204    |

### Pet fields

| Field       | Type    | Required | Notes          |
|-------------|---------|----------|----------------|
| `name`      | String  | Yes      |                |
| `species`   | String  | Yes      | e.g. Dog, Cat  |
| `age`       | Integer | No       | Must be >= 0   |
| `ownerName` | String  | No       |                |

### Example request

```json
POST /api/pets
{
  "name": "Buddy",
  "species": "Dog",
  "age": 3,
  "ownerName": "John"
}
```

### Example response

```json
{
  "id": 1,
  "name": "Buddy",
  "species": "Dog",
  "age": 3,
  "ownerName": "John"
}
```

## Error Responses

- `400 Bad Request` — missing required fields or invalid values (e.g. negative age)
- `404 Not Found` — pet with given ID does not exist
