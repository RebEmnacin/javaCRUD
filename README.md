# TaskManager

A task management REST API built with **Java**, **Spring Boot**, and **MySQL**. It supports basic CRUD operations on tasks and is the backend half of a planned full-stack project (a React frontend comes later).

> **Status:** In progress. Create, Read, and Delete are implemented. Update (PUT) and API testing are next.

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 4.0.8 |
| Build tool | Maven (with Maven Wrapper) |
| Database | MySQL 8.0 |
| Data access | Spring Data JPA / Hibernate |
| Web server | Embedded Tomcat (port 8080) |

## Project Structure

```
TaskManager/
├── pom.xml                          # Maven dependencies and build config
└── src/main/
    ├── java/com/Reb/TaskManager/
    │   ├── TaskManagerApplication.java   # App entry point (main method)
    │   ├── Task.java                     # Entity: one row in the tasks table
    │   ├── TaskRepository.java           # Database access (extends JpaRepository)
    │   ├── TaskService.java              # Business logic layer
    │   └── TaskController.java           # REST endpoints
    └── resources/
        └── application.properties        # Database connection settings
```

## Architecture

Each layer has one job and only talks to the layer directly below it:

```
Client → Controller → Service → Repository → MySQL
```

- **Entity (`Task`)**: describes what one task looks like
- **Repository**: saves, finds, and deletes tasks
- **Service**: holds the business logic and rules
- **Controller**: receives HTTP requests and returns JSON

## Data Model

Table: `tasks`

| Column | Type | Notes |
|---|---|---|
| `id` | Long | Primary key, auto-incremented |
| `title` | String | Task name |
| `description` | String | Task details |
| `status` | String | For example `todo` or `done` |
| `createdAt` | LocalDateTime | Creation timestamp |

The table is created automatically by Hibernate (`spring.jpa.hibernate.ddl-auto=update`).

## API Endpoints

Base URL: `http://localhost:8080/tasks`

| Method | Endpoint | Description | Status |
|---|---|---|---|
| GET | `/tasks` | Get all tasks | Done |
| GET | `/tasks/{id}` | Get one task by id | Done |
| POST | `/tasks` | Create a task | Done |
| DELETE | `/tasks/{id}` | Delete a task | Done |
| PUT | `/tasks/{id}` | Update a task | Planned |

### Example request body (POST)

```json
{
  "title": "Buy groceries",
  "description": "Milk and eggs",
  "status": "todo"
}
```

## Getting Started

### Prerequisites

- JDK 21
- MySQL 8.0 running on `localhost:3306`
- VS Code with the Extension Pack for Java (optional, any IDE works)

### 1. Create the database

```sql
CREATE DATABASE taskmanager;
```

### 2. Configure the connection

Edit `src/main/resources/application.properties`:

```properties
spring.application.name=TaskManager

spring.datasource.url=jdbc:mysql://localhost:3306/taskmanager
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD_HERE

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Replace `YOUR_PASSWORD_HERE` with your MySQL root password. Do not commit your real password to a public repository.

### 3. Run the app

From the `TaskManager` folder:

```bash
./mvnw spring-boot:run
```

On Windows PowerShell, `./mvnw spring-boot:run` also works. You can also run `TaskManagerApplication.java` directly from your IDE.

### 4. Try it

Open `http://localhost:8080/tasks` in a browser. An empty list (`[]`) means the app, database, and routes are all connected.

Use Postman or curl to test POST and DELETE.

## Roadmap

- [x] Project setup and MySQL connection
- [x] `Task` entity, repository, service, controller
- [x] GET, POST, and DELETE endpoints
- [ ] PUT (update) endpoint
- [ ] Test all endpoints with Postman
- [ ] Set `createdAt` automatically
- [ ] Reject empty titles in the service layer
- [ ] React frontend
- [ ] Connect frontend to backend

## What I Learned

- Layered architecture: Controller, Service, Repository
- Spring annotations: `@Entity`, `@Service`, `@RestController`, `@GetMapping`, and more
- Dependency injection through constructors
- Mapping Java classes to MySQL tables with JPA/Hibernate
- Handling `Optional` values with `orElseThrow`